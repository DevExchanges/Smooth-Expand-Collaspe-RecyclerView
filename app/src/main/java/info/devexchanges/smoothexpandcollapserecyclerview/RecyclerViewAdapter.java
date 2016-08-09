package info.devexchanges.smoothexpandcollapserecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ReyclerViewHolder> {
    private LayoutInflater layoutInflater;
    private HashSet<Integer> expandedPositionSet;
    private Context context;

    public RecyclerViewAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        expandedPositionSet = new HashSet<>();
        this.context = context;
    }

    @Override
    public ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(viewType == 0 ? R.layout.item_odd : R.layout.item_even, parent, false);

        return new ReyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ReyclerViewHolder holder, int position) {
        holder.updateItem(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class ReyclerViewHolder extends RecyclerView.ViewHolder {
        private ExpandableLayout expandableLayout;
        private TextView showInfo;

        private ReyclerViewHolder(final View view) {
            super(view);
            expandableLayout = (ExpandableLayout) view.findViewById(R.id.expandable_layout);
            showInfo = (TextView) view.findViewById(R.id.show_info);
        }

        private void updateItem(final int position) {
            expandableLayout.setOnExpandListener(new ExpandableLayout.OnExpandListener() {
                @Override
                public void onExpand(boolean expanded) {
                    registerExpand(position, showInfo);
                }
            });
            expandableLayout.setExpand(expandedPositionSet.contains(position));

        }
    }

    private void registerExpand(int position, TextView textView) {
        if (expandedPositionSet.contains(position)) {
            removeExpand(position);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);
            textView.setText("Show description");
            Toast.makeText(context, "Position: " + position + " collapsed!", Toast.LENGTH_SHORT).show();
        } else {
            addExpand(position);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);
            textView.setText("Hide description");
            Toast.makeText(context, "Position: " + position + " expanded!", Toast.LENGTH_SHORT).show();
        }
    }

    private void removeExpand(int position) {
        expandedPositionSet.remove(position);
    }

    private void addExpand(int position) {
        expandedPositionSet.add(position);
    }
}