package com.sedat.pagingdemo.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sedat.pagingdemo.adapter.RoomAdapter
import com.sedat.pagingdemo.databinding.ActivityRoomBinding
import com.sedat.pagingdemo.model.myClass
import com.sedat.pagingdemo.viewmodel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding
    private lateinit var viewModel: RoomViewModel
    @Inject
    lateinit var adapter: RoomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(RoomViewModel::class.java)
        val myList = arrayListOf<myClass>()
        for (i in 1..200){
            val _myClass = myClass(0, i)
            myList.add(_myClass)
        }

        viewModel.SaveData(myList)

        binding.recylerRoom.layoutManager = LinearLayoutManager(this)
        binding.recylerRoom.adapter = adapter

        lifecycleScope.launch {
            /*viewModel.data().collectLatest {
                adapter.submitData(it)
            }*/

            viewModel.data2().collectLatest {
                adapter.submitData(it)
            }
        }


    }
}