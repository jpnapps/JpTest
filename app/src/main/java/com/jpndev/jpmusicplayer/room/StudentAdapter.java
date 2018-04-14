package com.jpndev.jpmusicplayer.room;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by JAIGISH on 12-04-2018.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    private List<Student> studentList;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvname,tvgender,tvaddress,tvlocation;
        ImageView imageviewstudent;

        CardView card1;
        View mView;



        public MyViewHolder(View v) {
            super(v);
            mView = v;

            card1 = (CardView) v.findViewById(R.id.card1);
            tvname = (TextView) v.findViewById(R.id.tvname);
            tvgender = (TextView) v.findViewById(R.id.tvgender);
            tvaddress = (TextView) v.findViewById(R.id.tvaddress);
            tvlocation = (TextView) v.findViewById(R.id.tvlocation);
            imageviewstudent = (ImageView) v.findViewById(R.id.imageviewstudent);
        }
    }


    public StudentAdapter(List<Student> moviesList) {
        this.studentList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singlestudent, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Student movie = studentList.get(position);
        holder.tvname.setText(movie.getName());
        holder.tvgender.setText(movie.getGender());
        holder.tvaddress.setText(movie.getAddress());
        holder.tvlocation.setText(movie.getLocation());

       /* byte[] decodedString = Base64.decode(movie.getImage(),Base64.NO_WRAP);
        InputStream inputStream  = new ByteArrayInputStream(decodedString);
        Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
        holder.imageviewstudent.setImageBitmap(bitmap);*/

        byte[] data = movie.getImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        holder.imageviewstudent.setImageBitmap(bmp);

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}