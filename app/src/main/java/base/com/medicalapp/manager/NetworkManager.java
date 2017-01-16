package base.com.medicalapp.manager;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import base.com.medicalapp.R;
import base.com.medicalapp.adapter.ProductAdapter;
import base.com.medicalapp.utils.Utils;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;


public class NetworkManager {
    static final int DEFAULT_TIMEOUT = 60 * 1000;
    static ProductAdapter mJSONAdapter;

//http://stackoverflow.com/questions/16223286/getting-sockettimeoutexceptions-using-loopj-asynchttpclient-is-there-a-timeou
//http://loopj.com/android-async-http/

    private static AsyncHttpClient client;

    private  static synchronized AsyncHttpClient getClient() {
        if (client == null) {

            client = new AsyncHttpClient();
            client.setTimeout(DEFAULT_TIMEOUT);


        }
        return client;
    }

    public static void get(Context applicationContext, String url, RequestParams params, final NetworkInterface networkInterface) {
        if (!Utils.isInternetOn(applicationContext)) {
            networkInterface.onResponse(new ApiResponseWrapper(null, false, applicationContext.getResources().getString(R.string.no_network)));
            return;
        }
        getClient().get(getAbsoluteUrl(url), params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                //mJSONAdapter.updateData(response.optJSONArray("records"));
                networkInterface.onResponse(new ApiResponseWrapper(response, true, null));
                // If the response is JSONObject instead of expected JSONArray
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

               // GLog.log("ServerResponse>>"+errorResponse!= null?errorResponse.toString():"");
                networkInterface.onResponse(new ApiResponseWrapper(null, false, NetworkConstants.API_ERROR_MESSAGE));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //GLog.log("ServerResponse>>"+responseString);

                networkInterface.onResponse(new ApiResponseWrapper(null, false, NetworkConstants.API_ERROR_MESSAGE));

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                //GLog.log("ServerResponse>>"+errorResponse!= null?errorResponse.toString():"");

                networkInterface.onResponse(new ApiResponseWrapper(null, false, NetworkConstants.API_ERROR_MESSAGE));
            }
        });
    }



    private static String getAbsoluteUrl(String relativeUrl) {
        return NetworkConstants.BASE_URL + relativeUrl;
    }


    public interface NetworkInterface {

        public void onResponse(ApiResponseWrapper baseResponse);


    }

    public static void post(Context applicationContext, String url, HttpEntity entity, final NetworkInterface networkInterface) {


        if (!Utils.isInternetOn(applicationContext)) {
            networkInterface.onResponse(new ApiResponseWrapper(null, false, applicationContext.getResources().getString(R.string.no_network)));
            return;
        }
        getClient().post(applicationContext, getAbsoluteUrl(url), entity, NetworkConstants.REQUEST_BODY_CONTENT_TYPE, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

               // GLog.log("ServerResponse>>"+response!= null?response.toString():"");

                networkInterface.onResponse(new ApiResponseWrapper(response, true, null));
                // If the response is JSONObject instead of expected JSONArray
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
               // GLog.log("ServerResponse>>"+errorResponse!= null?errorResponse.toString():"");

                networkInterface.onResponse(new ApiResponseWrapper(null, false, NetworkConstants.API_ERROR_MESSAGE));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
               // GLog.log("ServerResponse>>"+responseString);

                networkInterface.onResponse(new ApiResponseWrapper(null, false, NetworkConstants.API_ERROR_MESSAGE));

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
               // GLog.log("ServerResponse>>"+errorResponse!= null?errorResponse.toString():"");

                networkInterface.onResponse(new ApiResponseWrapper(null, false, NetworkConstants.API_ERROR_MESSAGE));
            }
        });

    }



}
