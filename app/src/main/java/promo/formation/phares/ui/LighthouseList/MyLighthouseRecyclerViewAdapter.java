package promo.formation.phares.ui.LighthouseList;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import promo.formation.phares.MainActivity;
import promo.formation.phares.R;
import promo.formation.phares.lighthouse.LighthouseContent.LighthouseItem;

import java.text.BreakIterator;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link LighthouseItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyLighthouseRecyclerViewAdapter extends RecyclerView.Adapter<MyLighthouseRecyclerViewAdapter.ViewHolder> {

    public final List<LighthouseItem> mValues;

    public MyLighthouseRecyclerViewAdapter(List<LighthouseItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNomView.setText(mValues.get(position).nom);
        holder.mRegionView.setText(mValues.get(position).region);
        holder.mConstructionView.setText(String.valueOf(mValues.get(position).construction));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LighthouseItem item = mValues.get(position);
                Context context = MainActivity.getContext();
                Resources resources = context.getResources();
                CharSequence txt =
                        (item.nom == null ? "" : resources.getString(R.string.lighthouse_name) + " : " + item.nom) +
                        (item.region == null ? "" : "\n" + resources.getString(R.string.lighthouse_region) + " : " + item.region) +
                        (item.construction == 0 ? "" : "\n" + resources.getString(R.string.lighthouse_construction) + " : " + item.construction) +
                        (item.hauteur == 0 ? "" : "\n" + resources.getString(R.string.lighthouse_height ) + " : " + item.hauteur) +
                        (item.caractere == null ? "" : "\n" + resources.getString(R.string.lighthouse_type) + " : " + item.caractere) +
                        (item.eclat == 0 ? "" : "\n" + resources.getString(R.string.lighthouse_sparkle) + " : " + item.eclat) +
                        (item.periode == 0 ? "" : "\n" + resources.getString(R.string.lighthouse_period) + " : " + item.periode) +
                        (item.portee == 0 ? "" : "\n" + resources.getString(R.string.lighthouse_range) + " : " + item.portee) +
                        (item.automatisation == 0 ? "" : "\n" + resources.getString(R.string.lighthouse_automation) + " : " + item.automatisation) +
                        (item.lon == 0 || item.lat == 0 ? "" : "\n" + resources.getString(R.string.lighthouse_location) + " : " + item.lon + " " + item.lat);
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, txt, duration);
                toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public LighthouseItem mItem;
        public TextView mNomView;
        public TextView mRegionView;
        public TextView mConstructionView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNomView = (TextView) view.findViewById(R.id.nom);
            mRegionView = (TextView) view.findViewById(R.id.region);
            mConstructionView = (TextView) view.findViewById(R.id.construction);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNomView.getText() + "'";
        }
    }
}