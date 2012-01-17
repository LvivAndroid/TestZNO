package com.h.h.h;

import android.app.Application;

public class GlobalState extends Application {

	private String answ;
	private String corr;
	private int numb;
	private boolean back;

	public String getansw() {
		return answ;
	}

	public void setansw(String answ) {
		this.answ = answ;
	}

	public String getcorr() {
		return corr;
	}

	public void setcorr(String corr) {
		this.corr = corr;
	}

	public int getnumb() {
		return numb;
	}

	public void setnumb(int numb) {
		this.numb = numb;
	}

	public boolean getback() {
		return back;
	}

	public void setback(boolean back) {
		this.back = back;
	}
}