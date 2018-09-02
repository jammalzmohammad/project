package justparking.justparking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookingsAdapter extends ArrayAdapter<BookingParking> {

    private ArrayList<BookingParking> bookingParkings=new ArrayList<>();
    private Context context;
    public BookingsAdapter(@NonNull Context context, ArrayList<BookingParking> bookingParkings) {
        super(context, 0,bookingParkings);
        this.bookingParkings=bookingParkings;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.listbookings , parent , false);
        ImageView imageView = view.findViewById(R.id.imageView_list_item);
        TextView nameTextView = view.findViewById(R.id.bookingsName_list_item);
        TextView descTextView = view.findViewById(R.id.time_Bookings_list_item);
        nameTextView.setText(bookingParkings.get(position).getLoca());
        descTextView.setText(bookingParkings.get(position).getTime()+" Hour");
        return view;
    }
}
