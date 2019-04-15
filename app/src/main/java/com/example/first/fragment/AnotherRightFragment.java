package com.example.first.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.first.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @ClassName: AnotherRightFragment
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/4/5 18:52
 * @Version: 1.0
 */
public class AnotherRightFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.another_fragment_right, container,false);
        return view;
     }
}
