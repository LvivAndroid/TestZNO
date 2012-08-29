package com.testterra.main;

import android.app.Application;

public class GlobalState extends Application {

	private String answ;
	private String corr;
	private int numb;
	private boolean back;
	private boolean subm;
	private boolean ifsettingsChanged = false;

	private int NumberOfTests = 12;

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

	public boolean getsubm() {
		return subm;
	}

	public void setsubm(boolean subm) {
		this.subm = subm;
	}

	public int getNumberOfTests() {
		return NumberOfTests;
	}

	public void setNumbOfTests(int NumberOfTests) {
		this.NumberOfTests = NumberOfTests;
	}

	public boolean getIfSettingsChanged() {
		return subm;
	}

	public void setIfSettingsChanged(boolean ifsettingsChanged) {
		this.setIfsettingsChanged(ifsettingsChanged);
	}

	public boolean isIfsettingsChanged() {
		return ifsettingsChanged;
	}

	public void setIfsettingsChanged(boolean ifsettingsChanged) {
		this.ifsettingsChanged = ifsettingsChanged;
	}

}