package com.zouag.fsdocs.networking;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Moham on 1/22/2016.
 *
 * Stores different informations to do networking, including the HTTP routes.
 */
public class NodeAPI {
    public static final String SERVER_IP_ADDRESS = "http://192.168.173.1:5000";
    // Setup MIME type
    public static final MediaType JSON =
            MediaType.parse("application/json; charset=utf-8");

    private static final String STUDENT_SIGNUP_POST = "/student/signup";
    private static final String STUDENT_LOGIN_POST = "/login";

    /**
     * @param param  the request's body that contains the request's params to the server
     * @param url    target
     * @param method HTTP method (POST/GET)
     * @return a fully setup request
     */
    public static Request createHTTPRequest(JSONObject param, String url, Method method) {
        if (param == null)
            param = new JSONObject();

        try {
            // Specify the device type according to Node.js's API obligation
            param.put("device", "android");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return method == Method.POST ? new Request.Builder()
                .url(url)
                .post(RequestBody.create(NodeAPI.JSON, param.toString()))
                .build() :
                new Request.Builder()
                        .url(url + buildGETRequestParams(param))
                        .build();
    }

    /**
     * @param params JSONObject with the key/value params
     * @return ?key=value&
     */
    public static String buildGETRequestParams(JSONObject params) {
        StringBuilder sb = new StringBuilder("?");

        Iterator<String> keys = params.keys();
        while (keys.hasNext()) {
            try {
                String key = keys.next();
                sb.append(key)
                        .append("=")
                        .append(params.get(key));

                if (keys.hasNext())
                    sb.append("&");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    public static String getStudentSignupURL() {
        return SERVER_IP_ADDRESS + STUDENT_SIGNUP_POST;
    }

    public static String getStudentLoginURL() {
        return SERVER_IP_ADDRESS + STUDENT_LOGIN_POST;
    }
}
