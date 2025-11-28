import { getRestApiUrl } from "../utils/config";

async function parseJsonResponse(response) {
  if (!response.ok) {
    let errorText = "";
    try {
      errorText = await response.text();
    } catch (error) {
      // Ignore parsing error and fall back to status text
    }
    const message = errorText && errorText.trim().length > 0
      ? `${response.status} ${errorText}`
      : `Request failed with status ${response.status}`;
    throw new Error(message);
  }

  if (response.status === 204) {
    return null;
  }

  const contentType = response.headers.get("content-type") || "";
  if (!contentType.includes("application/json")) {
    const text = await response.text();
    throw new Error(`Expected JSON response but received: ${text.slice(0, 200)}`);
  }

  return response.json();
}

export async function fetchMaintenanceDraft(transformerId, inspectionId) {
  if (!transformerId) {
    throw new Error("transformerId is required to fetch maintenance draft");
  }
  const params = new URLSearchParams({ transformerId: String(transformerId) });
  if (inspectionId) {
    params.append("inspectionId", String(inspectionId));
  }

  const response = await fetch(
    getRestApiUrl(`maintenance-records/draft?${params.toString()}`),
    { credentials: "include" }
  );

  return parseJsonResponse(response);
}

export async function fetchMaintenanceRecord(recordId) {
  if (!recordId) {
    throw new Error("recordId is required to fetch maintenance record");
  }

  const response = await fetch(
    getRestApiUrl(`maintenance-records/${recordId}`),
    { credentials: "include" }
  );

  return parseJsonResponse(response);
}

export async function fetchMaintenanceHistory(transformerId, inspectionId) {
  if (!transformerId) {
    throw new Error("transformerId is required to fetch maintenance history");
  }

  const params = new URLSearchParams();
  if (inspectionId) {
    params.append("inspectionId", String(inspectionId));
  }

  const url = params.toString()
    ? getRestApiUrl(`maintenance-records/transformers/${transformerId}?${params.toString()}`)
    : getRestApiUrl(`maintenance-records/transformers/${transformerId}`);

  const response = await fetch(url, { credentials: "include" });

  return parseJsonResponse(response);
}

export async function saveMaintenanceRecord(payload) {
  if (!payload || !payload.transformerId) {
    throw new Error("payload with transformerId is required to save maintenance record");
  }

  const response = await fetch(
    getRestApiUrl("maintenance-records"),
    {
      method: payload.id ? "PUT" : "POST",
      headers: {
        "Content-Type": "application/json",
      },
      credentials: "include",
      body: JSON.stringify(payload),
    }
  );

  return parseJsonResponse(response);
}
