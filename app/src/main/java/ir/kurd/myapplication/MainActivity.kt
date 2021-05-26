package ir.kurd.myapplication

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import ir.kurd.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val viewModel = MainViewModel()
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeData()
    }

    private fun observeData() {
        viewModel.testApiLiveData.observe(this){
            binding.tvDate.text = it.createdDateTime.parseDate()
            binding.tvSpeed.text = it.speed.toString()
            binding.tvCoordinate.text = "${it.coordinates[0]},${it.coordinates[1]}"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposable.dispose()
    }
}