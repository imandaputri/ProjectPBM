package com.example.looting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BarangItemAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Barang> barangList;

    public BarangItemAdapter(Context context, int layout, ArrayList<Barang> barangList) {
        this.context = context;
        this.layout = layout;
        this.barangList = barangList;
    }

    @Override
    public int getCount() {
        return barangList.size();
    }

    @Override
    public Object getItem(int position) {
        return barangList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView gambarBarang;
        TextView namabarang2, deskbarang2;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.namabarang2 = (TextView) row.findViewById(R.id.namabarang2);
            holder.deskbarang2 = (TextView) row.findViewById(R.id.deskbarang2);
            holder.gambarBarang = (ImageView) row.findViewById(R.id.gambarbarang2);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Barang barang = barangList.get(position);

        holder.namabarang2.setText(barang.getNamabarang());
        holder.deskbarang2.setText(barang.getDeskbarang());

        byte[] barangImage = barang.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(barangImage, 0, barangImage.length);
        holder.gambarBarang.setImageBitmap(bitmap);

        return row;
    }
}
