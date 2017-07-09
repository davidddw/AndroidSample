package org.cloud.demo0baseframework.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.cloud.demo0baseframework.R;
import org.cloud.demo0baseframework.app.App;
import org.cloud.demo0baseframework.model.bean.Cast;
import org.cloud.demo0baseframework.model.bean.MovieInfoBean;
import org.cloud.demo0baseframework.utils.GlideHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author d05660ddw
 * @version 1.0 2017/7/8
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private Context mContext;
    private List<MovieInfoBean> mList;
    private LayoutInflater inflater;
    private int baseNumber;
    private OnItemClickListener mOnItemClickListener;

    public MovieListAdapter(Context mContext, List<MovieInfoBean> mList) {
        inflater = LayoutInflater.from(mContext);
        this.mList = mList;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    public void setList(List<MovieInfoBean> list) {
        mList.addAll(list);
    }

    public void setNewList(List<MovieInfoBean> list) {
        mList.clear();
        mList.addAll(list);
    }

    public int getListOldSize() {
        return mList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.movie_item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bindData(mList.get(position), position + 1);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClickListener(holder.itemView, holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageName)
        ImageView imageName;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tv_movie_doc)
        TextView tvMovieDoc;
        @BindView(R.id.tvCast)
        TextView tvCast;
        @BindView(R.id.tv_movie_type)
        TextView tvMovieType;
        @BindView(R.id.tv_movie_grade)
        TextView tvMovieGrade;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindData(final MovieInfoBean subject, int position) {
            String imagePath = subject.getImages().getLarge();
            GlideHelper.showImage(App.getInstance(), imagePath, imageName);
            String movieTitle = position + "、" + subject.getTitle() + "/" + subject.getOriginal_title();
            tvName.setText(movieTitle);
            String directoryName = "导演:" + subject.getDirectors().get(0).getName();
            tvMovieDoc.setText(directoryName);
            String movieCast = "主演:";
            for (Cast type : subject.getCasts()) {
                movieCast += type.getName() + " ";
            }
            tvCast.setText(movieCast);
            String movieType = subject.getYear() + " / ";
            for (String type : subject.getGenres()) {
                movieType += type + " ";
            }
            tvMovieType.setText(movieType);
            tvMovieGrade.setText(String.valueOf(subject.getRating().getAverage()));
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }
}
