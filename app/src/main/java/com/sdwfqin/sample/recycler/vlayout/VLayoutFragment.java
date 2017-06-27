package com.sdwfqin.sample.recycler.vlayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.bumptech.glide.Glide;
import com.sdwfqin.sample.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class VLayoutFragment extends Fragment {

    @BindView(R.id.main_view)
    RecyclerView mainView;
    Unbinder unbinder;
    private Context mContext;
    private View mView;
    private VirtualLayoutManager layoutManager;
    private List<DelegateAdapter.Adapter> adapters;

    public static VLayoutFragment newInstance() {
        VLayoutFragment fragment = new VLayoutFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_vlayout, container, false);
        unbinder = ButterKnife.bind(this, mView);
        initView();
        mContext = getActivity();
        return mView;
    }

    private void initView() {
        layoutManager = new VirtualLayoutManager(mContext);
        mainView.setLayoutManager(layoutManager);
        // 实现类似padding的效果
        mainView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0, 0, 0, 15);
            }
        });
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        mainView.setAdapter(delegateAdapter);
        adapters = new LinkedList<>();

        // banner图片
        List<String> imgs = new LinkedList<>();
        imgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498471769363&di=cd41ec2c450f3da07a11cf378fd47dfb&imgtype=0&src=http%3A%2F%2Fwww.1tong.com%2Fuploads%2Fwallpaper%2Flandscapes%2F273-1-1920x1200.jpg");
        imgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498471769361&di=3e0b350eecc9655fb72a7c1da7970245&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Ff7246b600c338744af80e6575b0fd9f9d72aa050.jpg");
        imgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1499066949&di=5ac6694646dd38015675ccaa28cea326&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.bz55.com%2Fuploads%2Fallimg%2F150317%2F140-15031G04118-50.jpg");
        imgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498472230107&di=339698d9da2f4e18e2bd23ebeb0a5cd7&imgtype=0&src=http%3A%2F%2Fimage227-c.poco.cn%2Fmypoco%2Fmyphoto%2F20140730%2F09%2F17362892420140730095950014_440.jpg%3F1024x763_120");
        imgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498472230093&di=285b7c5376e001dc3cab281aefbf5f7f&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fitbbs%2F1409%2F08%2Fc3%2F38363133_1410144825921.jpg");

        initBanner(imgs);
        initSticky();
        initGrid();
        initLinear();
        delegateAdapter.setAdapters(adapters);
    }

    private void initBanner(final List<String> imgs) {
        adapters.add(new SubAdapter(mContext, new LinearLayoutHelper(), 1) {

            @Override
            public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // 当viewtype == 0时返回banner的布局
                if (viewType == 1)
                    return new MainViewHolder(
                            LayoutInflater.from(mContext).inflate(R.layout.item_vlayout_banner, parent, false));

                return super.onCreateViewHolder(parent, viewType);
            }

            // 返回viewType，0
            @Override
            public int getItemViewType(int position) {
                return 1;
            }

            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                if (holder.itemView instanceof Banner) {
                    Banner banner = (Banner) holder.itemView;
                    //设置图片加载器
                    banner.setImageLoader(new GlideImageLoader());
                    //设置图片集合
                    banner.setImages(imgs);
                    //banner设置方法全部调用完毕时最后调用
                    banner.start();
                }
            }
        });
    }

    private void initSticky() {
        StickyLayoutHelper layoutHelper = new StickyLayoutHelper();
        // 设置偏移量
        // layoutHelper.setOffset(100);
        layoutHelper.setAspectRatio(4);
        layoutHelper.setBgColor(Color.GREEN);
        adapters.add(new SubAdapter(mContext, layoutHelper, 1));
    }

    private void initGrid() {
        GridLayoutHelper layoutHelper = new GridLayoutHelper(2);
        layoutHelper.setMargin(7, 0, 7, 0);
//        layoutHelper.setWeights(new float[]{46.665f});
        layoutHelper.setHGap(3);
        adapters.add(new SubAdapter(mContext, layoutHelper, 2));

        layoutHelper = new GridLayoutHelper(4);
//        layoutHelper.setWeights(new float[]{20f, 26.665f});
        layoutHelper.setMargin(7, 0, 7, 0);
        layoutHelper.setHGap(3);
        adapters.add(new SubAdapter(mContext, layoutHelper, 4));
    }

    private void initLinear() {
        LinearLayoutHelper layoutHelper = new LinearLayoutHelper();
        adapters.add(new SubAdapter(mContext, layoutHelper, 8));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * banner加载图片
     */
    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }
    }

    static class SubAdapter extends DelegateAdapter.Adapter<MainViewHolder> {

        private Context mContext;

        private LayoutHelper mLayoutHelper;

        private VirtualLayoutManager.LayoutParams mLayoutParams;
        private int mCount = 0;

        public SubAdapter(Context context, LayoutHelper layoutHelper, int count) {
            this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        public SubAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
            this.mContext = context;
            this.mLayoutHelper = layoutHelper;
            this.mCount = count;
            this.mLayoutParams = layoutParams;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return mLayoutHelper;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vlayout, parent, false));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            holder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(mLayoutParams));
        }

        /**
         * 跟onBindViewHolder方法一样，多了个参数offsetTotal，参数为item对应实际位置
         */
        @Override
        protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
            if (holder.itemView instanceof LinearLayout) {
                ((TextView) holder
                        .itemView
                        .findViewById(R.id.title))
                        .setText("position:" + position + "    offsetTotal:" + offsetTotal);
            }

        }

        @Override
        public int getItemCount() {
            return mCount;
        }
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {

        public MainViewHolder(View itemView) {
            super(itemView);
        }
    }
}