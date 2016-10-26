package com.yanglaoClient.mvp.entity;

import android.support.v4.app.Fragment;

public class FragmentEntity {
	private Fragment mFragment;
	private String fragmentLabel;

	public Fragment getmFragment() {
		return mFragment;
	}

	public void setmFragment(Fragment mFragment) {
		this.mFragment = mFragment;
	}

	public String getFragmentLabel() {
		return fragmentLabel;
	}

	public void setFragmentLabel(String fragmentLabel) {
		this.fragmentLabel = fragmentLabel;
	}


	public static FragmentEntity getFragmentEntity(Fragment fragment, String label) {
		FragmentEntity fe = new FragmentEntity();
		fe.setFragmentLabel(label);
		fe.setmFragment(fragment);

		return fe;
	}
}
