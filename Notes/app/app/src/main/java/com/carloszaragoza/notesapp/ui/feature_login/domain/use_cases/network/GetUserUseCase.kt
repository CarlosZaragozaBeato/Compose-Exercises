package com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.network

import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user.user
import com.carloszaragoza.notesapp.ui.feature_login.domain.repository.NetworkRepository
import com.carloszaragoza.test_retrofit.repository.DataOrException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: NetworkRepository
){
    suspend operator fun invoke(name:String, password:String):
            DataOrException<user?, Boolean, Exception> {

        val data: DataOrException<user? , Boolean, Exception> =
                DataOrException(null, false, Exception())
            try{
                data.loading = true
                data.data = repository.getUser(name = name,
                                                password = password)

                if(data.data != null){
                    data.loading = false
                }
            }catch (ex:Exception){
                data.e = ex
            }
            return data
    }
}