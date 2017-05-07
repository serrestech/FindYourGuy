package com.example.threedots.findyourguy.Common;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.threedots.findyourguy.Core.Adapters.roomAdapter;
import com.example.threedots.findyourguy.Data.DaoRoom;
import com.example.threedots.findyourguy.Data.DaoUser;
import com.example.threedots.findyourguy.Listeners.ListenerOnFinish;
import com.example.threedots.findyourguy.Model.Room;
import com.example.threedots.findyourguy.Model.User;
import com.example.threedots.findyourguy.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity{
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabLayout;
    public ConstraintLayout MyRoomTab,AllRoomsTab,Contact;
    public RecyclerView.LayoutManager layoutManager;
    private TextView textViewUsername;
    private User user;
    RecyclerView mRecyclerView;
    RecyclerView recAllRooms;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=getApplicationContext();
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            textViewUsername=(TextView) findViewById(R.id.textViewUsername);
            user=new User((String)b.get("UID"), (String)b.get("UserName"));
            textViewUsername.setText((String)b.get("UserName"));
        }else{
            finish();
        }
        mRecyclerView=(RecyclerView)findViewById(R.id.recMyRooms);
        recAllRooms=(RecyclerView)findViewById(R.id.recAllRooms);
        layoutManager=new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
        mRecyclerView.setHasFixedSize(true);

/*
        daoRoom=new DaoRoom( new User("kati","kati"),new ListenerOnFinish(){
            @Override
            public void OnFinish(ArrayList<Room> rooms) {
                tempList =rooms;
                roomAdapter mAdapter = new roomAdapter(tempList, new User("kati", "kati"),context, daoRoom);
                MainActivity.this.mRecyclerView.setAdapter(mAdapter);
            }
        }, context,false);*/

        layoutManager=new LinearLayoutManager(MainActivity.this);
        recAllRooms.setLayoutManager(layoutManager);
        recAllRooms.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
        recAllRooms.setHasFixedSize(true);
/*

        daoRoom=new DaoRoom( new User("kati","kati"),new ListenerOnFinish(){
            @Override
            public void OnFinish(ArrayList<Room> rooms) {
                tempList =rooms;
                roomAdapter mAdapter = new roomAdapter(tempList, new User("kati", "kati"),context, daoRoom);
                MainActivity.this.recAllRooms.setAdapter(mAdapter);
            }
        }, context,false);*/
        ButterKnife.bind(this);
        setupViewPager();
    }

    @OnClick(R.id.btn_Set_Main)
    public void onClick() {
        dialog();
    }

    private void setupViewPager(){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),MainActivity.this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void dialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_main_menu);
        Button ButtonUsrProf = (Button) dialog.findViewById(R.id.btn_user_profile);
        Button ButtonSettings = (Button) dialog.findViewById(R.id.btn_settings);
        Button ButtonLogout = (Button) dialog.findViewById(R.id.btn_logout);
        ButtonUsrProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DaoUser daoUser=new DaoUser(getApplicationContext(),user.getUserId());
                dialog.dismiss();
            }
        });
        ButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Settings not implemented yet" , Toast.LENGTH_SHORT ).show();
                dialog.dismiss();
            }
        });
        ButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        mRecyclerView.setAdapter(adapter);
    }

}

