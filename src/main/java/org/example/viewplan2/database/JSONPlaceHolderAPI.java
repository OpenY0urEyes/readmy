package org.example.viewplan2.database;

import org.example.viewplan2.models.Humans;
import org.example.viewplan2.models.Material;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface JSONPlaceHolderAPI {


    @GET("materials/{id}")
    Call<Material> getUser(@Path("id") Long id);
    @GET("human")
    Call<List<Humans>> getHumans();
}
