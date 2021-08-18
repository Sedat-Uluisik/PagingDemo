package com.sedat.pagingdemo

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.sedat.pagingdemo.adapter.ImageAdapter
import com.sedat.pagingdemo.databinding.ActivityMainBinding
import com.sedat.pagingdemo.room.RoomActivity
import com.sedat.pagingdemo.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ImageViewModel

    @Inject
    lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(ImageViewModel::class.java)

        binding.recylerView.layoutManager = LinearLayoutManager(this)
        binding.recylerView.adapter = adapter


        lifecycleScope.launch {
            /*viewModel.flow.collectLatest {
                adapter.submitData(it)
            }*/

            viewModel.data().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.roomActivity ->{
                val intent = Intent(this, RoomActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}