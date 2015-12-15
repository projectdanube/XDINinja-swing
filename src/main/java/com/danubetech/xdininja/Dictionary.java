package com.danubetech.xdininja;

import xdi2.core.syntax.XDIAddress;

public class Dictionary {

	public static XDIAddress XDI_FIRST_NAME = XDIAddress.create("<#first><#name>");
	public static XDIAddress XDI_LAST_NAME = XDIAddress.create("<#last><#name>");
	public static XDIAddress XDI_NICKNAME = XDIAddress.create("<#nickname>");
	public static XDIAddress XDI_GENDER = XDIAddress.create("<#gender>");
	public static XDIAddress XDI_BIRTH_DATE = XDIAddress.create("<#birth><#date>");
	public static XDIAddress XDI_NATIONALITY = XDIAddress.create("<#nationality>");
	public static XDIAddress XDI_PHONE = XDIAddress.create("<#phone>");
	public static XDIAddress XDI_MOBILE_PHONE = XDIAddress.create("<#mobile><#phone>");
	public static XDIAddress XDI_WORK_PHONE = XDIAddress.create("<#work><#phone>");
	public static XDIAddress XDI_EMAIL = XDIAddress.create("<#email>");
	public static XDIAddress XDI_WEBSITE = XDIAddress.create("<#website>");
	public static XDIAddress XDI_ADDRESS_STREET = XDIAddress.create("#address<#street>");
	public static XDIAddress XDI_ADDRESS_COUNTRY = XDIAddress.create("#address<#country>");
	public static XDIAddress XDI_ADDRESS_LOCALITY = XDIAddress.create("#address<#locality>");
	public static XDIAddress XDI_ADDRESS_POSTAL_CODE = XDIAddress.create("#address<#postal><#code>");
	public static XDIAddress XDI_ADDRESS_REGION = XDIAddress.create("#address<#region>");

	public static XDIAddress[] DICTIONARY = new XDIAddress[] {

			XDI_FIRST_NAME,
			XDI_LAST_NAME,
			XDI_NICKNAME,
			XDI_GENDER,
			XDI_BIRTH_DATE,
			XDI_NATIONALITY,
			XDI_PHONE,
			XDI_MOBILE_PHONE,
			XDI_WORK_PHONE,
			XDI_EMAIL,
			XDI_WEBSITE,
			XDI_ADDRESS_STREET,
			XDI_ADDRESS_COUNTRY,
			XDI_ADDRESS_LOCALITY,
			XDI_ADDRESS_POSTAL_CODE,
			XDI_ADDRESS_REGION
	};
}
