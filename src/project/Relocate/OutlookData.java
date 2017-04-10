package project.Relocate;

import java.io.IOException;

public class OutlookData {
	private final String Title,CPP,Trends,TrendsDate,Lang,ProvAbbr,Location;
	private final int potential,code,provID, NOC, cityID;
	
	/**
	 * A class to hold data scraped from the outlooks data set
	 * @param c A numerical ID for the job
	 * @param title The title of the job
	 * @param pot The numerical ranking (0-3) of the job's potential
	 * @param cp The string representation of the job's potential
	 * @param tr A string explaining the reason for the job's outlook score
	 * @param trd The date the outlook was decided
	 * @param lang The language of the outlook's trend score
	 * @param provCd A numerical representation of the province 
	 * @param prov A two letter abbreviation for the province name
	 * @param code A numerical code for the location of this outlook
	 * @param locName The name of the location for this outlook
	 */
	
	public OutlookData(int c, String title, int pot, String cp, String tr, String trd,String lang, int provCd, String prov, int code, String locName, int cityID){
		NOC = c;
		Title = title;
		potential = pot;
		CPP = cp;
		Trends = tr;
		TrendsDate = trd;
		Lang = lang;
		provID = provCd;
		ProvAbbr = prov;
		if(locName.contains("(province)")){
			//If this is a provincial data row we want the code to be 0 to signify this
			this.code = 0;
			//Having location name here is redundant so it will be nulled out
			this.Location = null;
		} else {
			this.code = code;
			this.Location = locName.split("-")[0];
		}
		this.cityID = 0;
	}
	
	/**
	 * Returns the numerical ID of a job
	 * @return An integer representation of a job's numerical ID
	 */
	public int getNOC() {
		return NOC;
	}
	
	/**
	 * Returns the numerical code for a location in the dataset
	 * @return An integer representation of the numerical code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * Returns the string representation of a job's potential
	 * @return The string representation of a job's potential
	 */
	public String getCPP() {
		return CPP;
	}
	/**
	 * Returns the language the trend data is in
	 * @return The string representation of the language the data is in
	 */
	public String getLang() {
		return Lang;
	}
	/**
	 * Returns the name of the location this data was collected in
	 * @return The string representation of the location this data was collected in
	 */
	public String getLocation() {
		return Location;
	}
	/**
	 * Returns the numerical potential of a job
	 * @return The integer representation of a job's potential
	 */
	public int getPotential() {
		return potential;
	}
	/**
	 * Returns the provincial abbreviation for a job
	 * @return A string containing a province's abbreviation
	 */
	public String getProvAbbr() {
		return ProvAbbr;
	}
	/**
	 * Returns the numerical provincial ID code
	 * @return An integer holding the province's numerical ID
	 */
	public int getProvID() {
		return provID;
	}
	/**
	 * Returns the title of the job
	 * @return A string containing the job's title
	 */
	public String getTitle() {
		return Title;
	}
	/**
	 * Returns a paragraph on the reason for a job's outlook
	 * @return A string holding reasoning for the job's outlook
	 */
	public String getTrends() {
		return Trends;
	}
	/**
	 * Returns the date the outlook was decided
	 * @return A string representing the date the job's outlook was decided
	 */
	public String getTrendsDate() {
		return TrendsDate;
	}
	
	public int getCityID() {
		return this.cityID;
	}
	
}