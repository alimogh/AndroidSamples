package com.sdwfqin.sample.animator.animator1;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sdwfqin.sample.R;
import com.sdwfqin.sample.view.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 使用属性动画ObjectAnimator修改属性时要确保原始对象有get和set方法，如果没有可以尝试用一个类来包装原始对象。
 * 也可以使用ValueAnimator，监听动画实现过程
 * <p>
 * ObjectAnimator在修改属性时通过不断调用属性的get和set方法实现动画效果。
 */
public class Animator1Activity extends AppCompatActivity {

    @BindView(R.id.animator1_btn)
    Button animator1Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator1);
        ButterKnife.bind(this);

        animator1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewWrapper viewWrapper = new ViewWrapper(animator1Btn);
                ObjectAnimator.ofInt(viewWrapper, "width",
                        Utils.dp2px(Animator1Activity.this, 300))
                        .setDuration(5000)
                        .start();
            }
        });
    }

    /**
     * 用一个类来包装原始对象，间接为其提供get和set方法
     */
    private static class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }

    }
}
