package com.pulkit.githubapplication.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.pulkit.githubapplication.BR;
import com.pulkit.githubapplication.R;
import com.pulkit.githubapplication.databinding.ItemPullRequestBinding;
import com.pulkit.githubapplication.model.PullRequest;

import java.util.List;

public class GithubPullRequestRecyclerAdapter extends RecyclerView.Adapter<GithubPullRequestRecyclerAdapter.ViewHolder> {

    private List<PullRequest> pullRequests;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemPullRequestBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_pull_request, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setVariable(BR.pullRequest, pullRequests.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return pullRequests == null ? 0 : pullRequests.size();
    }

    public void updateRequest(PullRequest pullRequest) {
        pullRequests.set(pullRequests.indexOf(pullRequest), pullRequest);
        notifyItemChanged(pullRequests.indexOf(pullRequest));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        final ItemPullRequestBinding binding;

        public ViewHolder(ItemPullRequestBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


    }

    public void setNewsArticles(List<PullRequest> pullRequests){
        this.pullRequests = pullRequests;
        notifyDataSetChanged();
    }
}
