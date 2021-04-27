package com.example.widas.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.widas.network.RetrofitClient;
import com.example.widas.network.UserApi;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private MutableLiveData<Object> mutableLiveData = new MutableLiveData<>();

    public Repository() {

    }

    public MutableLiveData<? extends Object> getMutableLiveData() {

        final UserApi service = RetrofitClient.getService();
        Call<DataResponse> call = service.getData(20);//Just add 20 for checking for time being
        assert call != null;
        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(@NotNull Call<DataResponse> call, @NotNull Response<DataResponse> response) {
                DataResponse dataResponse = response.body();
                if (dataResponse != null) {
                    Log.d("RahulDebug", dataResponse.toString());
                    mutableLiveData.setValue(dataResponse.getResults());
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.d("RahulDebug:-", t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
