package com.study.hong.zyhdemo.frame.glide.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.study.hong.zyhdemo.R;
import com.study.hong.zyhdemo.customize.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by hong on 2019/6/6.
 * 不要忘记增加    compile 'jp.wasabeef:glide-transformations:2.0.1'特效合集
 */

public class GlideTranformationsAdapter extends RecyclerView.Adapter<GlideTranformationsAdapter.ViewHolder>  {

    private Context mContext;
    private List<String> mData = new ArrayList<>();

    public GlideTranformationsAdapter(Context context) {
        mContext = context;
        for (int i = 1; i <= 21; i++) {
            mData.add(i + "");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = View.inflate(mContext, R.layout.glide_item_tranformations, null);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // 设置名称
        holder.name.setText("item" + (position + 1));

        int integer = Integer.parseInt(mData.get(position));
//        compile 'jp.wasabeef:glide-transformations:2.0.1'
        //不要忘记添加特效合集库的依赖
        switch (integer) {
            case 1: {
                int width = Utils.dip2px( 133.33f,mContext);
                int height = Utils.dip2px(126.33f,mContext);
                Glide.with(mContext)
                        .load(R.drawable.glide_tranformations_adapter_check)
                        .override(width, height)
                        .bitmapTransform(new CenterCrop(mContext),
                                new MaskTransformation(mContext, R.drawable.glide_tranformations_adapter_mask_starfish))
                        .into(holder.image);
                break;
            }
            case 2: {
                int width = Utils.dip2px(150.0f,mContext);
                int height = Utils.dip2px( 100.0f,mContext);
                Glide.with(mContext)
                        .load(R.drawable.glide_tranformations_adapter_check)
                        .override(width, height)
                        .bitmapTransform(new CenterCrop(mContext),
                                new MaskTransformation(mContext, R.drawable.glide_tranformations_adapter_mask_starfish))
                        .into(holder.image);
                break;
            }
            case 3:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .bitmapTransform(
                                new CropTransformation(mContext, 300, 100, CropTransformation.CropType.TOP))
                        .into(holder.image);
                break;
            case 4:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .bitmapTransform(new CropTransformation(mContext, 300, 100))
                        .into(holder.image);
                break;
            case 5:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .bitmapTransform(
                                new CropTransformation(mContext, 300, 100, CropTransformation.CropType.BOTTOM))
                        .into(holder.image);

                break;
            case 6:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .bitmapTransform(new CropSquareTransformation(mContext))
                        .into(holder.image);
                break;
            case 7:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .bitmapTransform(new CropCircleTransformation(mContext))
                        .into(holder.image);
                break;
            case 8:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .bitmapTransform(new ColorFilterTransformation(mContext, Color.argb(80, 255, 0, 0)))
                        .into(holder.image);
                break;
            case 9:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .bitmapTransform(new GrayscaleTransformation(mContext))
                        .into(holder.image);
                break;
            case 10:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .bitmapTransform(new RoundedCornersTransformation(mContext, 30, 0,
                                RoundedCornersTransformation.CornerType.BOTTOM))
                        .into(holder.image);
                break;
            case 11:
                Glide.with(mContext)
                        .load(R.drawable.glide_tranformations_adapter_check)
                        .bitmapTransform(new BlurTransformation(mContext, 25))
                        .into(holder.image);
                break;
//            case 12:
//                Glide.with(mContext)
//                        .load(R.drawable.demo)
//                        .error(R.drawable.glide_tranformations_adapter_check)
//                        .bitmapTransform(new ToonFilterTransformation(mContext))
//                        .into(holder.image);
//                break;
//            case 13:
//                Glide.with(mContext)
//                        .load(R.drawable.glide_tranformations_adapter_check)
//                        .error(R.drawable.glide_tranformations_adapter_check)
//                        .bitmapTransform(new SepiaFilterTransformation(mContext))
//                        .into(holder.image);
//                break;
//            case 14:
//                Glide.with(mContext)
//                        .load(R.drawable.glide_tranformations_adapter_check)
//                        .error(R.drawable.glide_tranformations_adapter_check)
//                        .bitmapTransform(new ContrastFilterTransformation(mContext, 2.0f))
//                        .into(holder.image);
//                break;
//            case 15:
//                Glide.with(mContext)
//                        .load(R.drawable.glide_tranformations_adapter_check)
//                        .bitmapTransform(new InvertFilterTransformation(mContext))
//                        .into(holder.image);
//                break;
//            case 16:
//                Glide.with(mContext)
//                        .load(R.drawable.glide_tranformations_adapter_check)
//                        .bitmapTransform(new PixelationFilterTransformation(mContext, 20))
//                        .into(holder.image);
//                break;
//            case 17:
//                Glide.with(mContext)
//                        .load(R.drawable.glide_tranformations_adapter_check)
//                        .bitmapTransform(new SketchFilterTransformation(mContext))
//                        .into(holder.image);
//                break;
//            case 18:
//                Glide.with(mContext)
//                        .load(R.drawable.glide_tranformations_adapter_check)
//                        .bitmapTransform(
//                                new SwirlFilterTransformation(mContext, 0.5f, 1.0f, new PointF(0.5f, 0.5f)))
//                        .into(holder.image);
//                break;
//            case 19:
//                Glide.with(mContext)
//                        .load(R.drawable.glide_tranformations_adapter_check)
//                        .bitmapTransform(new BrightnessFilterTransformation(mContext, 0.5f))
//                        .into(holder.image);
//                break;
//            case 20:
//                Glide.with(mContext)
//                        .load(R.drawable.glide_tranformations_adapter_check)
//                        .bitmapTransform(new KuwaharaFilterTransformation(mContext, 25))
//                        .into(holder.image);
//                break;
//            case 21:
//                Glide.with(mContext)
//                        .load(R.drawable.glide_tranformations_adapter_check)
//                        .bitmapTransform(new VignetteFilterTransformation(mContext, new PointF(0.5f, 0.5f),
//                                new float[]{0.0f, 0.0f, 0.0f}, 0f, 0.75f))
//                        .into(holder.image);
//                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.iv_glide_tranfromations);
            name=itemView.findViewById(R.id.tv_glide_name);
        }
    }
}
