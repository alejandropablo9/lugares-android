package mx.com.tejate.testservice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import mx.com.tejate.testservice.Config.Util;
import mx.com.tejate.testservice.models.Place;

public class PlacesAdapter extends ArrayAdapter<Place> {



    public PlacesAdapter(@NonNull Context context, @NonNull List<Place> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder;


        if (null == convertView) {
            //Si no existe, entonces inflarlo
            convertView = inflater.inflate(
                    R.layout.item_list,
                    parent,
                    false);

            holder = new ViewHolder();
            holder.picture = convertView.findViewById(R.id.iv_place);
            holder.title = convertView.findViewById(R.id.tv_title);
            holder.geolocation = convertView.findViewById(R.id.tv_geolocation);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Place place = getItem(position);

        holder.title.setText(place.getTitle());
        holder.geolocation.setText(place.getCoordenadas());
        if ( place.getUrl() != null && !place.getUrl().equals("")) {

            try {
                Picasso.get()
                        .load(place.getUrl())
                        .into(holder.picture);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Picasso.get()
                    .load(R.drawable.marker)
                    .into(holder.picture);
        }


        return convertView;
    }

    static class ViewHolder {
        ImageView picture;
        TextView title;
        TextView geolocation;
    }
}
