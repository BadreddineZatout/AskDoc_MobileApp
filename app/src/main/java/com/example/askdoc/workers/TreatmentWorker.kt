package com.example.askdoc.workers

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import androidx.work.impl.utils.futures.SettableFuture
import com.example.askdoc.models.Traitement
import com.example.askdoc.services.RetrofitService
import com.example.askdoc.services.RoomService
import com.google.common.util.concurrent.ListenableFuture
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TreatmentWorker(val ctx: Context, val workParamters:
WorkerParameters) : ListenableWorker(ctx, workParamters) {
    lateinit var future: SettableFuture<Result>
    @SuppressLint("RestrictedApi")
    override fun startWork(): ListenableFuture<Result> {
        future = SettableFuture.create()
        offlineTreatemnts()
        return future 
    }
    fun offlineTreatemnts(){
        val call = RetrofitService.endpoint.getAllTreaitementOffline()
        call.enqueue(object: Callback<List<Traitement>> {
            @SuppressLint("RestrictedApi")
            override fun onResponse(call: Call<List<Traitement>>, response: Response<List<Traitement>>) {
                if(response?.isSuccessful){
                    val data = response.body()!!
                    RoomService.appDatabase.getTraitementDao().addTreatments(data)
                    updateOffline()
                }else{
                    future.set(Result.retry())
                }
            }

            @SuppressLint("RestrictedApi")
            override fun onFailure(call: Call<List<Traitement>>, t: Throwable) {
                future.set(Result.retry())
            }
        })
    }
    fun updateOffline(){
        val call = RetrofitService.endpoint.updateOffline()
        call.enqueue(object: Callback<String> {
            @SuppressLint("RestrictedApi")
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response?.isSuccessful){

                }else{
                    future.set(Result.retry())
                }
            }

            @SuppressLint("RestrictedApi")
            override fun onFailure(call: Call<String>, t: Throwable) {
                future.set(Result.retry())
            }
        })
    }
}