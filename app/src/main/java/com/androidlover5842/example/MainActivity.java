package com.androidlover5842.example;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.androidlover5842.example.databinding.ActivityMainBinding;

public class MainActivity extends FragmentActivity implements View.OnClickListener {


    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel=new ViewModelProvider(this).get(MainViewModel.class);
        binding.btProgress.setOnClickListener(this);

        viewModel.getCounter().observe(this,meterModel -> {
            binding.btProgress.loading(false);
            binding.meter.setPosition(meterModel.getPosition());
            binding.meter.setText(meterModel.getText());
        });

        setupRecycler();
        binding.recycler.setAdapter(viewModel.getAdapter());
    }

    private Runnable loading;
    private Handler handler;
    private void setupRecycler(){
        handler=new Handler();

        loading=()->{
            binding.recycler.setLoading(!binding.recycler.isLoading());
            if (handler!=null)
            handler.postDelayed(loading,1000);
        };
        handler.postDelayed(loading,1000);
    }
    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==v.getId()){
            binding.btProgress.loading(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler=null;
    }
}