package com.example.gemapp.Retrofit;

import com.example.gemapp.models.Clients;
import com.example.gemapp.models.Employes;
import com.example.gemapp.models.Interventions;
import com.example.gemapp.models.Taches;


import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface INodeJS {



    @POST("/register/")
    @FormUrlEncoded
    Observable<String> registerEmploye (@Field("login") String login,
                                     @Field("pwd") String pwd,
                                     @Field("prenom") String prenom,
                                     @Field("nom") String nom,
                                     @Field("email") String email,
                                     @Field("actif") int actif,
                                     @Field("valsync") int valsync);

    @POST("/login/")
    @FormUrlEncoded
    Observable<String> loginEmploye (@Field("login") String login,
                                  @Field("pwd") String pwd);

    @GET("/Employes/{login}")
    Call<Employes> getUser(@Path("login") String login);

    @GET("/GetTasks/{intervention_id}")
    Call<List<Taches>>gettaches(@Path("intervention_id") int intervention_id);


    @POST("/GetinterventionEmploye/")
    @FormUrlEncoded
    Call<List<Interventions>> getintervList(@Field("employe_id") int employe_id,@Field("datedebut") String datedebut);




    @PUT("/intervention/update/{id}")
    @FormUrlEncoded
    Observable<String> updateinterv(
            @Path("intervention_id") int intervention_id,
                             @Field("titre") String titre,
                             @Field("commentaire") String commentaire,
                             @Field("datedebut") String datedebut,
                             @Field("datefin") String datefin);

    @DELETE("/intervention/delete/{id}")
    Call<Interventions> deleteintervention(@Path("id") int id);

    @GET("/Getclient/")
    Call<List<Clients>> getClient();





}
