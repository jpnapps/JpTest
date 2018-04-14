package com.jpndev.jpmusicplayer.cdegen;

import android.content.Context;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mani-Ceino on 4/14/2018.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.jpndev.jpmusicplayer.R;
import com.jpndev.jpmusicplayer.retrofit.ApiIClient;
import com.jpndev.jpmusicplayer.retrofit.ApiInterface;

import okhttp3.MediaType;
import okhttp3.RequestBody;


public class NetworkApi
		 {

public static final String HEADER_KEY1 = "support-app-id";
public static final String HEADER_VALUE1="appid";

public static final String HEADER_KEY2 = "support-app-key";
public static final String HEADER_VALUE2="appkey";



static NetworkCallApi mNetworkCallApi;
public KProgressHUD hud_progress = null;

public static NetworkCallApi getInstance() {
		if(mNetworkCallApi==null)
		mNetworkCallApi = new NetworkCallApi();
		return mNetworkCallApi;
		}

/**
 * WEb requests
 */
public void putWebJSONObject(final Context mContext, final  String qsid , JSONObject input, AjaxCallback<JSONObject> cb)
		{
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		aq.put(GLOBALURL + "question/" + qsid, input, JSONObject.class, cb);
		}




public  void putInfodWebJSONObject(final Context mContext,final  String qsid ,JSONObject input,AjaxCallback<JSONObject> cb)
		{
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		aq.post(NetworkService.GLOBALURL + "question/additionalinfo/" + qsid, input, JSONObject.class, cb);

		}



public  void putMultipartWebJSONObject(final Context mContext, Map<String, Object> params, AjaxCallback<JSONObject> cb)
		{
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		cb.header("enctype", "multipart/form-data");
		aq.ajax(NetworkService.SIGN_GLOBALURL + "register", params, JSONObject.class, cb);
		}



public  void putregisterPostWebJSONObject(final Context mContext, JSONObject input, AjaxCallback<JSONObject> cb)
		{
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		aq.post(NetworkService.SIGN_GLOBALURL + "register", input, JSONObject.class, cb);
		}


public void putprofileUpdateMultipartJSONObject(Context mContext, String profileID, Map<String, Object> params, AjaxCallback<JSONObject> cb)
		{
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		cb.header("enctype", "multipart/form-data");
		aq.ajax(NetworkService.GLOBALURL + "user/edit/" + profileID, params, JSONObject.class, cb);
		}


public void putqsShareFileMultipartJSONObject(Context mContext, String url, Map<String, Object> params, AjaxCallback<JSONObject> cb)
		{
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		cb.header("enctype", "multipart/form-data");
		aq.ajax(url, params, JSONObject.class, cb);
		}

public void putqsShareMultipartJSONObject(Context mContext, String url, Map<String, Object> params, AjaxCallback<JSONObject> cb)
		{
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		cb.header("enctype", "multipart/form-data");
		aq.ajax(url, params, JSONObject.class, cb);
		}

public void putqsVideoPostWebJSONObject(Context mContext, String url, Map<String, Object> params, AjaxCallback<JSONObject> cb)
		{
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		cb.header("enctype", "multipart/form-data");
		aq.ajax(url, params, JSONObject.class, cb);
		}


public void putlayerAnnotateWebJSONObject(Context mContext, JSONObject json2, AjaxCallback<JSONObject> cb)
		{
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		cb.header("Content-Type", "application/json");
		aq.post(NetworkService.GLOBALURL + "classes/chat", json2, JSONObject.class, cb);
		}

public void putpriorityPostWebObject(Context mContext, String qsid, JSONObject input, AjaxCallback<JSONObject> cb)
		{
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		aq.post(NetworkService.GLOBALURL + "question/changepriority/" + qsid, input, JSONObject.class, cb);
		}

public void putdeleteInstallationWebObject(Context mContext, String url, AjaxCallback<JSONObject> cb)
		{
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		aq.delete(url, JSONObject.class, cb);
		}

public void putchangePwdWebJSONObject(Context mContext, String profileID, JSONObject input, AjaxCallback<JSONObject> cb)
		{
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		aq.put(NetworkService.GLOBALURL + "user/changepassword/" + profileID, input, JSONObject.class, cb);
		}


public void putopenOrResolvedQscallJSONObject(Context mContext, String url, AjaxCallback<JSONObject> cb) {

		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		aq.ajax(url,JSONObject.class, cb);
		}

public void putopenOrResolvedQscall_else_JSONObject(Context mContext, String companyid, String userid, boolean flag, int skip, int limit, AjaxCallback<JSONObject> cb) {

		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		try {
		aq.ajax(NetworkService.GLOBALURL + "question?where=" + URLEncoder.encode("{\"companyid\":\""+companyid+"\",\"userid\":\""+userid+"\",\"isResolved\":"+flag+"}","UTF-8")+"&skip=" +skip+"&limit="+limit, JSONObject.class, cb);
		} catch (UnsupportedEncodingException e) {

		}
		}

public void putHighPriorityQsWebJSONObject(Context mContext, String url, AjaxCallback<JSONObject> cb) {
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		aq.ajax(url,JSONObject.class, cb);
		}

public void putHighPriorityQsWeb_else_JSONObject(Context mContext, String companyid, String userid, String priority, int skip, int limit, AjaxCallback<JSONObject> cb) {

		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		try {
		aq.ajax(NetworkService.GLOBALURL + "question?where=" + URLEncoder.encode("{\"companyid\":\""+companyid+"\",\"userid\":\""+userid+"\",\"priority\":"+priority+"}","UTF-8")+"&skip=" +skip+"&limit="+limit, JSONObject.class, cb);
		} catch (UnsupportedEncodingException e) {

		}

		}


public void putcriticalPriorityQsWebJSONObject(Context mContext, String url, AjaxCallback<JSONObject> cb) {
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		aq.ajax(url,JSONObject.class, cb);
		}

public void putcriticalPriorityQsWeb_else_JSONObject(Context mContext, String companyid, String userid, String priority, int skip, int limit, AjaxCallback<JSONObject> cb) {

		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		try {
		aq.ajax(NetworkService.GLOBALURL + "question?where=" + URLEncoder.encode("{\"companyid\":\""+companyid+"\",\"userid\":\""+userid+"\",\"priority\":"+priority+"}","UTF-8")+"&skip=" +skip+"&limit="+limit, JSONObject.class, cb);
		} catch (UnsupportedEncodingException e) {

		}

		}



public void putopenOrCloseQscallJSONObject(Context mContext, String companyid, String profileid, String userid, boolean flag, int skip, int limit, AjaxCallback<JSONObject> cb) {
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		try {
		aq.ajax(NetworkService.GLOBALURL + "question?where=" + URLEncoder.encode("{\"companyid\":\""+companyid+"\",\"shareto\":\""+profileid+"\",\"userid\":\""+userid+"\",\"isResolved\":"+flag+"}","UTF-8")+"&skip=" +skip+"&limit="+limit, JSONObject.class, cb);
		} catch (UnsupportedEncodingException e) {

		}
		}

public void putrepOpenOrCloseQscallJSONObject(Context mContext, String companyid, String userid, String profileid, boolean flag, int skip, int limit, AjaxCallback<JSONObject> cb) {
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		try {
		aq.ajax(NetworkService.GLOBALURL + "question?where=" + URLEncoder.encode("{\"companyid\":\""+companyid+"\",\"shareto\":\""+userid+"\",\"userid\":\""+profileid+"\",\"isResolved\":"+flag+"}","UTF-8")+"&skip=" +skip+"&limit="+limit, JSONObject.class, cb);
		} catch (UnsupportedEncodingException e) {

		}
		}

public void putdocMakeFavWebJSONobject(Context mContext, JSONObject input, AjaxCallback<JSONObject> cb) {
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		cb.header("Content-Type", "application/json");
		aq.post(NetworkService.GLOBALURL + "classes/favorites", input, JSONObject.class, cb);
		}

public void putdocDeleteFavWebJSONobject(Context mContext, String url, AjaxCallback<JSONObject> cb) {
		AQuery aq=new AQuery(mContext);
		addHeaders(cb);
		aq.delete(url,JSONObject.class,cb);
		}

public void putsearchFeedJSONobject(Context mContext, String url, AjaxCallback<JSONObject> cb) {
		AQuery aq=new AQuery(mContext);
		addHeaders(cb);
		aq.ajax(url, JSONObject.class, cb);
		}

public void putsearchDocumentsJSONobject(Context mContext, String searchtext, int skip, int limit, String json, AjaxCallback<JSONObject> cb) {
		AQuery aq=new AQuery(mContext);
		addHeaders(cb);
		aq.ajax(GLOBALURL + "search/library?field=title&query=" + searchtext+ "&skip=" + skip + "&limit=" + limit +  "&where=" + json + "", JSONObject.class, cb);

		}

public void putJSONsearchAllQuestionsobject(Context mContext, String url, AjaxCallback<JSONObject> cb) {
		AQuery aq=new AQuery(mContext);
		addHeaders(cb);
		aq.ajax(url, JSONObject.class, cb);
		}

public void putqsUnresolvedWebJSONobject(Context mContext,String url, JSONObject input, AjaxCallback<JSONObject> cb) {

		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		aq.post(url, input, JSONObject.class, cb);
		}

public void putqsCallHistoryWebJSONobject(Context mContext, String url, AjaxCallback<JSONObject> cb) {
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		aq.ajax(NetworkService.GLOBALURL + url, JSONObject.class, cb);
		}


public void putwebGetTemplatesJSONobject(Context mContext, String url, AjaxCallback<JSONObject> cb) {
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		aq.ajax(GLOBALURL + url, JSONObject.class, cb);
		}


public void putwebGetTemplateCategoriesJSONobject(Context mContext, String url, AjaxCallback<JSONObject> cb) {
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		aq.ajax(GLOBALURL + url, JSONObject.class, cb);
		}

public void putwebGetCategoriesJSONobject(Context mContext, String url, AjaxCallback<JSONObject> cb) {
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		aq.ajax(GLOBALURL + url, JSONObject.class, cb);
		}


public void putJwebGetRespLocationSONobject(Context mContext, String url, AjaxCallback<JSONObject> cb) {
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		aq.ajax(NetworkService.GLOBALURL + url, JSONObject.class, cb);
		}

public void putwebGetUserRepAllListJSONobject(Context mContext, String url, AjaxCallback<JSONObject> cb) {
		AQuery aq = new AQuery(mContext);
		addHeaders(cb);
		aq.ajax(GLOBALURL + url, JSONObject.class, cb);
		}
public void putwebGetUserRepListJSONobject(Context mContext, String url, AjaxCallback<JSONObject> cb) {
		AQuery aq=new AQuery(mContext);
		addHeaders(cb);
		aq.ajax(GLOBALURL + url, JSONObject.class, cb);
		}

public void putwebGetAZUserRepListJSONobject(Context mContext, String url, AjaxCallback<JSONObject> cb) {
		AQuery aq=new AQuery(mContext);
		addHeaders(cb);
		aq.ajax(GLOBALURL + url, JSONObject.class, cb);
		}

public void putwebGetLocationBYCompanyJSONobject(Context mContext, String url, AjaxCallback<JSONObject> cb) {
		AQuery aq=new AQuery(mContext);
		addHeaders(cb);
		aq.ajax(NetworkService.GLOBALURL + url, JSONObject.class, cb);
		}
public void putsendInvitationJSONobject(FragmentActivity activity, AjaxCallback<JSONObject> cb) {
		AQuery aq=new AQuery(mContext);
		addHeaders(cb);
		aq.ajax(NetworkService.GLOBALURL + "user/approve", JSONObject.class, cb);

		}

private void addHeaders(AjaxCallback<JSONObject> cb) {
		cb.header(HEADER_KEY1, HEADER_VALUE1);
		cb.header(HEADER_KEY2,HEADER_VALUE2);
		}













			 /**vineesh retrofit **/

			 private void qsSharePostWeb(QuestionCreateInput qscreate) {
				 try {

					 setLoading("Your question is being added");
					 showProgress();
					 Gson gson = new Gson();
					 String json = gson.toJson(qscreate);

					 //{"category":"Engineering","companyid":"5768bcde305f76765da3f97c","deviceType":"android","shareto":["5795d62e305f4d498cb22a61","57888920305fe5162d23eed3"],"title":"2+5+67*56+8999???","userid":"5795d62e305f4d498cb22a61","username":"Amal E","isResolved":false,"isSharedToAll":false}



					 ApiInterface apiService = ApiIClient.getClient().create(ApiInterface.class);

					 RequestBody data = RequestBody.create(MediaType.parse("multipart/form-data"), json);

					 Call<SuccesMessage> call = apiService.getUploadedStatus(data);
					 call.enqueue(new Callback<SuccesMessage>() {
						 @Override
						 public void onResponse(Call<SuccesMessage> call, Response<SuccesMessage> response) {
							 int statusCode = response.code();




							 hideProgress();

							 if (response.isSuccessful())
							 {


								 SuccesMessage.Success[] sucesss=response.body().getSuccess();

								 Log.d("HAISIZESUCCESS",String.valueOf(sucesss.length));
								 if(isValid(sucesss)) {
									 // Constants.annotedlist.clear();
									 Constants.annotedlist = null;
									 Constants.categorylist = null;
									 // Constants.sharedlist.clear();
									 Constants.sharedlist = null;
									 // Constants.question_template_ids=null;
									 Constants.assign_user_id=null;
									 Constants.question_image = null;
									 Constants.question_origin_image=null;
									 Constants.question_image_data = null;
									 Constants.selectall_flag=false;
									 Constants.filepath=null;
									 Constants.clearInformations();
									 //  showHomeActivty();
									 //NetworkService.sort_pref=Constants.SORT_NEWEST;
									 //NetworkService.startActionAll(getActivity());




									 new Handler().postDelayed(new Runnable() {
										 @Override
										 public void run() {

											 NetworkService.startActionAllQuestions(getActivity(),0,10);
											 NetworkService.startActionMineQuestions(getActivity(),0,10);

										 }
									 }, 2000);
									 hideProgress();
									 setStatus("Question Added", R.color.md_green_400);
									 showStatus();
									 new Handler().postDelayed(new Runnable() {
										 @Override
										 public void run() {
											 showFragmentHomeActivity(new QsFragment());
											 //  showFragmentHomeActivity(new QuestionsFragment());


											 hideStatus();

										 }
									 }, 1000);


								 } else {

									 ToastHandler.newInstance(getActivity()).mustShowToast("Please try again ");
									 //postEventOnMainThread(new NotifyEvent());
								 }


							 }
							 else
							 {
								 Toast.makeText(getActivity(), "please try again  ", Toast.LENGTH_SHORT);

							 }





						 }

						 @Override
						 public void onFailure(Call<SuccesMessage> call, Throwable t) {
							 // Log error here since request failed
							 Toast.makeText(getActivity(), "Something went wrong !"+String.valueOf(t), Toast.LENGTH_SHORT);

							 hideProgress();

						 }
					 });


				 } catch (Exception e){Crashlytics.logException(e);

				 }

			 }

/* //  compile 'com.google.code.gson:gson:2.2.4'
    compile 'com.google.code.gson:gson:2.3.0'
    compile 'com.squareup.retrofit2:retrofit:2.3.0'
    compile 'com.squareup.retrofit2:converter-gson:2.3.0'*/



























			 private void qsShareFileMultipart(QuestionCreateInput qscreate) throws Exception {

				 String filename = Constants.filepath.substring(Constants.filepath.lastIndexOf("/") + 1);
				 String fileformat = Constants.filepath.substring(Constants.filepath.lastIndexOf(".") + 1);
				 qscreate.format=fileformat+"";
				 qscreate.fileName=filename+"";
				 setLoading("Your question is being added");
				 showProgress();
				 Gson gson = new Gson();
				 String json = gson.toJson(qscreate);
				 String url = NetworkService.GLOBALURL + "question";
				 Map<String, Object> params = new HashMap<String, Object>();
				 params.put("data", json);
				 File file = new File(Constants.filepath);
				 params.put("file", file);
				 // }

				 AjaxCallback<JSONObject> cb = new AjaxCallback<JSONObject>() {
					 @Override
					 public void callback(String url, JSONObject json, AjaxStatus status) {
						 //   Log.e("resposne", json.toString());
						 try {
							 hideProgress();
							 if(json == null) {
								 ToastHandler.newInstance(getActivity()).mustShowToast(" Please try again ");
							 } else {
								 if(json != null) {
									 SuccesMessage sucesss = GsonUtils.getInstance().gsonToSuccesMessage(json);
									 if(isValid(sucesss)) {
										 // Constants.annotedlist.clear();
										 Constants.annotedlist = null;
										 Constants.categorylist = null;
										 Constants.filepath=null;
										 // Constants.sharedlist.clear();
										 Constants.sharedlist = null;
										 Constants.assign_user_id=null;
										 Constants.question_image = null;
										 Constants.question_origin_image=null;
										 Constants.question_image_data = null;
										 Constants.selectall_flag=false;
										 Constants.external_id=null;
										 Constants.information=null;
										 Constants.clearInformations();
										 //  showHomeActivty();
										 //NetworkService.sort_pref=Constants.SORT_NEWEST;
										 //   NetworkService.startActionAll(getActivity());


										 new Handler().postDelayed(new Runnable() {
											 @Override
											 public void run() {

												 NetworkService.startActionAllQuestions(getActivity(),0,10);
												 NetworkService.startActionMineQuestions(getActivity(),0,10);

											 }
										 }, 2000);
										 hideProgress();
										 setStatus("Question Added", R.color.md_green_400);
										 showStatus();
										 new Handler().postDelayed(new Runnable() {
											 @Override
											 public void run() {

												 showFragmentHomeActivity(new QsFragment());
												 // showFragmentHomeActivity(new QuestionsFragment());
												 hideStatus();

											 }
										 }, 1000);

									 } else {

										 ToastHandler.newInstance(getActivity()).mustShowToast("Please try again ");
										 //postEventOnMainThread(new NotifyEvent());
									 }
								 }

							 }
						 } catch (Exception e){

							 Crashlytics.logException(e);
							 hideProgress();
							 // e.printStackTrace();
							 ToastHandler.newInstance(getActivity()).mustShowToast("Please try again " );
						 }


					 }
				 };
				 AQuery aq = new AQuery(getActivity());
				 cb.header("support-app-id", "appid");
				 cb.header("support-app-key", "appkey");
				 cb.header("enctype", "multipart/form-data");
				 aq.ajax(url, params, JSONObject.class, cb);
			 }


			 private void qsShareMultipart(QuestionCreateInput qscreate) throws Exception {



				 setLoading("Your question is being added");
				 showProgress();
				 Gson gson = new Gson();
				 String json = gson.toJson(qscreate);
				 String url = NetworkService.GLOBALURL + "question";
				 Map<String, Object> params = new HashMap<String, Object>();
				 params.put("data", json);
				 Bitmap bitmap = Constants.question_image;
				 //  if(isValid(bitmap)) {
				 ByteArrayOutputStream bos = new ByteArrayOutputStream();
				 bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
				 byte[] data = bos.toByteArray();
				 params.put("image", data);
				 // }

				 AjaxCallback<JSONObject> cb = new AjaxCallback<JSONObject>() {
					 @Override
					 public void callback(String url, JSONObject json, AjaxStatus status) {
						 //   Log.e("resposne", json.toString());
						 try {
							 hideProgress();
							 if(json == null) {
								 ToastHandler.newInstance(getActivity()).mustShowToast(" Please try again ");
							 } else {
								 if(json != null) {
									 SuccesMessage sucesss = GsonUtils.getInstance().gsonToSuccesMessage(json);
									 if(isValid(sucesss)) {
										 // Constants.annotedlist.clear();
										 Constants.annotedlist = null;
										 Constants.categorylist = null;
										 // Constants.sharedlist.clear();
										 Constants.sharedlist = null;
										 Constants.assign_user_id=null;
										 Constants.question_image = null;
										 Constants.question_origin_image=null;
										 Constants.question_image_data = null;
										 Constants.selectall_flag=false;
										 Constants.external_id=null;
										 Constants.filepath=null;
										 Constants.information=null;
										 Constants.clearInformations();
										 //  showHomeActivty();
										 //NetworkService.sort_pref=Constants.SORT_NEWEST;
										 //   NetworkService.startActionAll(getActivity());


										 new Handler().postDelayed(new Runnable() {
											 @Override
											 public void run() {

												 NetworkService.startActionAllQuestions(getActivity(),0,10);
												 NetworkService.startActionMineQuestions(getActivity(),0,10);

											 }
										 }, 2000);
										 hideProgress();
										 setStatus("Question Added", R.color.md_green_400);
										 showStatus();
										 new Handler().postDelayed(new Runnable() {
											 @Override
											 public void run() {

												 showFragmentHomeActivity(new QsFragment());
												 // showFragmentHomeActivity(new QuestionsFragment());
												 hideStatus();

											 }
										 }, 1000);

									 } else {

										 ToastHandler.newInstance(getActivity()).mustShowToast("Please try again ");
										 //postEventOnMainThread(new NotifyEvent());
									 }
								 }

							 }
						 } catch (Exception e){

							 Crashlytics.logException(e);
							 hideProgress();
							 // e.printStackTrace();
							 ToastHandler.newInstance(getActivity()).mustShowToast("Please try again " );
						 }


					 }
				 };
				 AQuery aq = new AQuery(getActivity());
				 cb.header("support-app-id", "appid");
				 cb.header("support-app-key", "appkey");
				 cb.header("enctype", "multipart/form-data");
				 aq.ajax(url, params, JSONObject.class, cb);
			 }
		}

