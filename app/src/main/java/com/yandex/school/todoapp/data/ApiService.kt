package com.yandex.school.todoapp.data


import com.yandex.school.todoapp.data.dtos.TaskTodoDto
import com.yandex.school.todoapp.data.dtos.TaskTodoSingleDto
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface  TaskApi {
    @GET("/list")
    suspend fun fetchTasks(): TaskTodoDto

    @GET("/list/{id}")
    suspend fun fetchTaskById(id: Int): TaskTodoSingleDto

    @POST("/list")
    suspend fun addTask(@Body task: TaskTodoSingleDto): Any

    @PUT("/list/{id}")
    suspend fun updateTask(@Body task: TaskTodoSingleDto): Any

    @DELETE("/list/{id}")
    suspend fun deleteTask(id: Int): Any
}

private  val client = OkHttpClient.Builder().build()

private val retrofit = Retrofit.Builder().baseUrl("https://beta.mrdekk.ru/todo").client(client).addConverterFactory(GsonConverterFactory.create()).build()

val taskApi: TaskApi = retrofit.create(TaskApi::class.java)

