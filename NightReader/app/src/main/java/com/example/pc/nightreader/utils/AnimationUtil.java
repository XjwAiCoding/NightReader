package com.example.pc.nightreader.utils;

import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * View Gone Visible Animation 
 *  注意：需导入https://github.com/JakeWharton/NineOldAndroids项目gradle依赖
 * @author 
 *
 */
public class AnimationUtil {

	/**
	 * 
	 * To animate view slide out from left to rightbnn
	 * 
	 * @param pView
	 */
	public static void slideToRightGone(final View pView, long pDuration, final OnAnimationEndListener pOnAnimationEndListener) {
		if(pDuration <= 0 ){
			pDuration = 300;
		}

		ObjectAnimator _ObjectAnimator = ObjectAnimator.ofFloat(pView, "translationX", 0f, pView.getWidth());//需要被操作的View,运行的时长,初始值和结束值
		/*_ObjectAnimator.setStartDelay(200);//延迟多少时间启动动画
	   _ObjectAnimator.setRepeatMode(ObjectAnimator.RESTART);//顺序重复
		_ObjectAnimator.setRepeatMode(ObjectAnimator.REVERSE);//倒序重复
		_ObjectAnimator.setRepeatCount(4);//重复次数*/
		_ObjectAnimator.setDuration(pDuration);
		_ObjectAnimator.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator arg0) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animator arg0) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator arg0) {
				pView.setVisibility(View.GONE);
				pOnAnimationEndListener.onAnimationEnd();
			}
			
			@Override
			public void onAnimationCancel(Animator arg0) {
				
			}
		});
		_ObjectAnimator.start();

		/*很多时候我们并不想要监听那么多个事件，可能我只想要监听动画结束一个事件或者其中任意几个事件*/
		/*_ObjectAnimator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
			}

		});*/

	}

	/**
	 * Left Gone
	 *
	 * To animate view slide out from right to left
	 * 
	 * @param pView
	 */
	public static void slideToLeftGone(final View pView, long pDuration, final OnAnimationEndListener pOnAnimationEndListener) {
		if(pDuration <= 0 ){
			pDuration = 300;
		}
		ObjectAnimator _ObjectAnimator = ObjectAnimator.ofFloat(pView, "translationX", 0f, -pView.getWidth());
		_ObjectAnimator.setDuration(pDuration);

		_ObjectAnimator.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator arg0) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animator arg0) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator arg0) {
				pView.setVisibility(View.GONE);
				pOnAnimationEndListener.onAnimationEnd();
			}
			
			@Override
			public void onAnimationCancel(Animator arg0) {
				
			}
		});
		_ObjectAnimator.start();
	}

	/**
	 * Left Visible
	 *
	 * To animate view slide in from left to right
	 *
	 * @param pView
	 */
	public static void slideToLeftVisible(final View pView, long pDuration, final OnAnimationEndListener pOnAnimationEndListener) {
		if(pDuration <= 0 ){
			pDuration = 300;
		}
		ObjectAnimator _ObjectAnimator = ObjectAnimator.ofFloat(pView, "translationX", -pView.getWidth(), 0f);
		_ObjectAnimator.setDuration(pDuration);
		_ObjectAnimator.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator arg0) {
				pView.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationRepeat(Animator arg0) {

			}

			@Override
			public void onAnimationEnd(Animator arg0) {
				pOnAnimationEndListener.onAnimationEnd();
			}

			@Override
			public void onAnimationCancel(Animator arg0) {

			}
		});
		_ObjectAnimator.start();
	}

	/**
	 * Bottom Gone
	 * 
	 * To animate view slide out from top to bottom
	 * 
	 * @param pView
	 */
	public static void slideToBottomGone(final View pView, long pDuration, final OnAnimationEndListener pOnAnimationEndListener) {
		if(pDuration <= 0 ){
			pDuration = 300;
		}
		ObjectAnimator _ObjectAnimator = ObjectAnimator.ofFloat(pView, "translationY", 0f, pView.getHeight());
		_ObjectAnimator.setDuration(pDuration);
		_ObjectAnimator.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator arg0) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animator arg0) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator arg0) {
				pView.setVisibility(View.GONE);
				pOnAnimationEndListener.onAnimationEnd();
			}
			
			@Override
			public void onAnimationCancel(Animator arg0) {
				
			}
		});
		_ObjectAnimator.start();
		
	}
	
	/**
	 * Bottom Visible
	 * 
	 * To animate view slide in from top to bottom
	 * 
	 * @param pView
	 */
	public static void slideToBottomVisible(final View pView, long pDuration, final OnAnimationEndListener pOnAnimationEndListener) {
		if(pDuration <= 0 ) pDuration = 300;
		ObjectAnimator _ObjectAnimator = ObjectAnimator.ofFloat(pView, "translationY", pView.getHeight(), 0f);
		_ObjectAnimator.setDuration(pDuration);
		_ObjectAnimator.setInterpolator(new DecelerateInterpolator(2));
		_ObjectAnimator.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator arg0) {
				pView.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onAnimationRepeat(Animator arg0) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator arg0) {
				pOnAnimationEndListener.onAnimationEnd();
			}
			
			@Override
			public void onAnimationCancel(Animator arg0) {
				
			}
		});
		_ObjectAnimator.start();
	}

	/**
	 * Gone
	 * 
	 * To animate view slide out from bottom to top
	 * 
	 * @param pView
	 */
	public static void slideToTopGone(final View pView, long pDuration, final OnAnimationEndListener pOnAnimationEndListener) {
		if(pDuration <= 0 ){
			pDuration = 300;
		}
		ObjectAnimator _ObjectAnimator = ObjectAnimator.ofFloat(pView, "translationY", 0f, -pView.getHeight());
		_ObjectAnimator.setDuration(pDuration);
		_ObjectAnimator.setInterpolator(new DecelerateInterpolator(2));
		_ObjectAnimator.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator arg0) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animator arg0) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator arg0) {
				pView.setVisibility(View.GONE);
				pOnAnimationEndListener.onAnimationEnd();
			}
			
			@Override
			public void onAnimationCancel(Animator arg0) {
				
			}
		});
		_ObjectAnimator.start();
	}
	
	/**
	 * Visible
	 * 
	 * To animate view slide in from bottom to top
	 * 
	 * @param pView
	 */
	public static void slideToTopVisible(final View pView, long pDuration, final OnAnimationEndListener pOnAnimationEndListener) {
		if(pDuration <= 0 ) pDuration = 300;
		ObjectAnimator _ObjectAnimator = ObjectAnimator.ofFloat(pView, "translationY", -pView.getHeight(), 0f);
		_ObjectAnimator.setDuration(300);
		_ObjectAnimator.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator arg0) {
				pView.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onAnimationRepeat(Animator arg0) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator arg0) {
				pOnAnimationEndListener.onAnimationEnd();
			}
			
			@Override
			public void onAnimationCancel(Animator arg0) {
				
			}
		});
		_ObjectAnimator.start();
	}

	/**
	 * 旋转
	 *
	 * @param pView View
	 * @param pStartAngle StartAngle
	 * @param pEndAngle EndAngle
	 */
	public static void rotation(final View pView, float pStartAngle, float pEndAngle){
		ObjectAnimator.ofFloat(pView, "rotation", pStartAngle, pEndAngle).setDuration(300).start();
	}

	/**
	 * AnimationEndListener
	 */
	public interface OnAnimationEndListener{
		void onAnimationEnd();
	}
}
