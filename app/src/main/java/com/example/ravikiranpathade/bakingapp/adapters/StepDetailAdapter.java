package com.example.ravikiranpathade.bakingapp.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ravikiranpathade.bakingapp.R;
import com.example.ravikiranpathade.bakingapp.singleList.StepForRecipe;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ravikiranpathade on 10/25/17.
 */

public class StepDetailAdapter extends RecyclerView.Adapter<StepDetailAdapter.StepDetailViewHolder> {

    StepForRecipe allDetails;
    Context context;
    public SimpleExoPlayer getExoplayer;



    public StepDetailAdapter(StepForRecipe details) {
        allDetails = details;

    }

    @Override
    public StepDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.step_detail_item;
        context = parent.getContext();
        boolean shouldAttach = false;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutId, parent, shouldAttach);
        StepDetailViewHolder stepDetails = new StepDetailViewHolder(view);
        ButterKnife.bind(context, view);
        return stepDetails;
    }

    @Override
    public void onBindViewHolder(StepDetailViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class StepDetailViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.step_description)
        TextView stepDescriptionItem;
        Context holderContext;
        public SimpleExoPlayer exoPlayer;
        public @BindView(R.id.exoPlayer)
        SimpleExoPlayerView playerView;
        public SimpleExoPlayer getExoPlayer() {
            return exoPlayer;
        }

        public SimpleExoPlayerView getPlayerView() {
            return playerView;
        }
        public StepDetailViewHolder(View itemView) {
            super(itemView);
            holderContext = itemView.getContext();

            //
            //Set Click Listener here if required

        }

        public void bind(int position) {
            ButterKnife.bind(this, itemView);

            playerView.setDefaultArtwork(BitmapFactory.decodeResource
                    (context.getResources(), R.drawable.exo_controls_play));
            Log.d("Video URL", "Check"+String.valueOf(exoPlayer==null));

            if (allDetails.getVideoUrl().length()>10) {
                //Log.d("Av At"+String.valueOf(position),allDetails.getVideoUrl());
                initializePlayer(Uri.parse(allDetails.getVideoUrl()));

            }else{
                //Log.d("NA At"+String.valueOf(position),allDetails.getVideoUrl());
                playerView.setVisibility(View.GONE);
            }
            stepDescriptionItem.setText(allDetails.getDesc());
            getExoplayer = exoPlayer;
        }

        private void initializePlayer(Uri parse) {
            if (exoPlayer == null) {
                TrackSelector trackSelector = new DefaultTrackSelector();
                LoadControl loadControl = new DefaultLoadControl();
                exoPlayer = ExoPlayerFactory.newSimpleInstance(holderContext, trackSelector, loadControl);


                playerView.setPlayer(exoPlayer);
                //MediaSource

                String agent = Util.getUserAgent(context, "Something to test");
                MediaSource mediaSource = new ExtractorMediaSource(parse, new DefaultDataSourceFactory(context, agent),
                        new DefaultExtractorsFactory(), null, null);
                exoPlayer.prepare(mediaSource);
                exoPlayer.setPlayWhenReady(true);

            }
        }

    }
}
