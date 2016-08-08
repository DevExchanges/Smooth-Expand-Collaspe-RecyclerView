package info.devexchanges.smoothexpandcollapserecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashSet;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ReyclerViewHolder> {
    private LayoutInflater layoutInflater;
    private HashSet<Integer> expandedPositionSet;

    public RecyclerViewAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        expandedPositionSet = new HashSet<>();
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

        private ReyclerViewHolder(final View view) {
            super(view);
            expandableLayout = (ExpandableLayout) view.findViewById(R.id.expandable_layout);
        }

        private void updateItem(final int position) {
            expandableLayout.setOnExpandListener(new ExpandableLayout.OnExpandListener() {
                @Override
                public void onExpand(boolean expanded) {
                    registerExpand(position);
                }
            });
            expandableLayout.setExpand(expandedPositionSet.contains(position));

        }
    }

    private void registerExpand(int position) {
        if (expandedPositionSet.contains(position)) {
            removeExpand(position);
        } else {
            addExpand(position);
        }
    }

    private void removeExpand(int position) {
        expandedPositionSet.remove(position);
    }

    private void addExpand(int position) {
        expandedPositionSet.add(position);
    }
}