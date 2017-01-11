package base.com.medicalapp.manager;

import org.json.JSONObject;

public class ApiResponseWrapper {
    public ApiResponseWrapper(JSONObject jsonObjectResponse, boolean success, String message) {
        this.jsonObjectResponse = jsonObjectResponse;
        this.success = success;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    private JSONObject jsonObjectResponse;
    private boolean success;

    public JSONObject getJsonObjectResponse() {
        return jsonObjectResponse;
    }

    public void setJsonObjectResponse(JSONObject jsonObjectResponse) {
        this.jsonObjectResponse = jsonObjectResponse;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
