package com.example.hwhan.rrealfinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.daum.mf.map.api.CameraPosition;
import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.CancelableCallback;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class MapFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener, MapView.MapViewEventListener, MapView.POIItemEventListener, MapView.CurrentLocationEventListener, MainActivity.OnBackPressedListener {
    String CURRENT_LOCATION = "";
    private MapPoint CURRENT_POINT;

    private MapReverseGeoCoder mReverseGeoCoder = null;
    private MapView mapView;

    private MapPOIItem mDefaultMarker;


    private EditText mEditTextQuery;
    private Button search, menu;
    private FloatingActionButton currentLocation;


    private InputMethodManager imm;
    String locate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.map_fragment, container, false);




//        imm = (InputMethodManager)getSystemService((INPUT_METHOD_SERVICE));

        //지도용
        mapView = view.findViewById(R.id.map_view);
        mapView.setMapViewEventListener(this);
        mapView.setPOIItemEventListener(this);

        mapView.setZoomLevel(11,true);


        mEditTextQuery = view.findViewById(R.id.editTextQuery);
        menu = view.findViewById(R.id.navigationBtn);
        search = view.findViewById(R.id.SearchBtn);
        currentLocation = view.findViewById(R.id.locBtn);

        Bundle bundle= this.getArguments();
        if(bundle!=null){
            if(bundle.getString("title")!=""){
                String title = bundle.getString("title");
                Toast.makeText(getContext(), title+"222222", Toast.LENGTH_LONG).show();
                finder(title);
            }
        }

        mEditTextQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    searcher(v);
                }
                return false;
            }
        });


        currentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                searcher(view);

            }
        });


        return view;
    }

    //지명으로 좌표검색
    public static String finder(String locate){
        RestTask Task1 = new RestTask();
        String url = "https://dapi.kakao.com/v2/local/search/address.json?query="+locate;
        String jsonString = "";
        Task1.setAurl(url);
        try {
            jsonString = Task1.execute().get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    //좌표로 지명검색
    public static String finder2(double x, double y){
        RestTask task = new RestTask();
        String url = "https://dapi.kakao.com/v2/local/geo/coord2address.json?x="+x+"&y="+y+"&input_coord=WGS84";
        String jsonString=null;
        task.setAurl(url);
        try {
            jsonString = task.execute().get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsonString;


    }



    //검색바 메소드
    public void searcher(final View view){
        final String[] temp;
        temp = localjsonParser(finder(mEditTextQuery.getText().toString()));
        if(temp[0]!= null){
            CameraPosition Temp = new CameraPosition(MapPoint.mapPointWithGeoCoord(Double.parseDouble(temp[1]), Double.parseDouble(temp[0])), 5);
            mapView.animateCamera(CameraUpdateFactory.newCameraPosition(Temp), 500, new CancelableCallback() {
                @Override
                public void onFinish() {
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
                @Override
                public void onCancel() {
                    Toast.makeText(getActivity(), "canceled", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(getActivity(), "No Search data error", Toast.LENGTH_SHORT).show();
        }
    }


    //좌표파싱
    public  String[] coorjsonParser(String jsonString){
        String adress_name=null;
        String region_1depth_name=null;
        String region_2depth_name=null;
        String region_3depth_name=null;
        Log.i("provideMsg : ", jsonString);
        String[] arraysum = new String[4];

        try {
            JSONArray jarray = new JSONObject(jsonString).getJSONArray("documents");
            Log.i("provideMsg : ", jarray.getJSONObject(0).toString());


            JSONObject jObject = jarray.getJSONObject(0);
            JSONObject jObject2 = jObject.getJSONObject("address");
//            Log.i("provideMsg : ", jObject.getJSONArray("address").toString());

            adress_name = jObject2.optString("address_name"); //주소지
            region_1depth_name = jObject2.optString("region_1depth_name"); //도
            region_2depth_name = jObject2.optString("region_2depth_name"); //시
            region_3depth_name = jObject2.optString("region_3depth_name"); //면


            arraysum[0] = adress_name;
            arraysum[1] = region_1depth_name;
            arraysum[2] = region_2depth_name;
            arraysum[3] = region_3depth_name;
            Log.i("provideMsg : ", adress_name);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arraysum;



    }

    //지명파싱
    public String[] localjsonParser(String jsonString){

        String x=null;
        String y=null;


        String[] arraysum = new String[2];

        try {
            JSONArray jarray = new JSONObject(jsonString).getJSONArray("documents");
            for (int i = 0; i < jarray.length(); i++) {
                HashMap map = new HashMap<>();
                JSONObject jObject = jarray.getJSONObject(i);

                x = jObject.optString("x");
                y = jObject.optString("y");



                arraysum[0] = x;
                arraysum[1] = y;
                Log.i("provideMsg : ", x);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arraysum;

    }



    //마커 메소드
    private void createDefaultMarker(MapView mapView, double x, double y, String name) {
        MapPoint CurrentPoint = MapPoint.mapPointWithGeoCoord(x, y);
        mDefaultMarker = new MapPOIItem();
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(0);
        mDefaultMarker.setMapPoint(CurrentPoint);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);


        mapView.addPOIItem(mDefaultMarker);
        mapView.selectPOIItem(mDefaultMarker, true);
        mapView.setMapCenterPoint(CurrentPoint, false);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {


//        imm.hideSoftInputFromWindow(mEditTextQuery.getWindowToken(), 0); //키보드 숨기기
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        CURRENT_POINT = MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude);

        final String[] temp = coorjsonParser(finder2(mapPointGeo.longitude, mapPointGeo.latitude));
        if(temp[0]!= null){
            mapView.removeAllPOIItems(); //기존 마커 제거
            CURRENT_LOCATION = temp[0];
            createDefaultMarker(mapView, CURRENT_POINT.getMapPointGeoCoord().latitude, CURRENT_POINT.getMapPointGeoCoord().longitude, CURRENT_LOCATION);//선택위치기준 마커생성
            Toast.makeText(getActivity(), "현재주소: " + temp[1] +" "+ temp[2], Toast.LENGTH_SHORT).show();
            locate = temp[1] +" "+ temp[2];

        }
        else{
            mapView.removeAllPOIItems(); //기존 마커 제거
            Toast.makeText(getActivity(), "지원하지 않는 지역입니다.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
        //맵이동시 현위치고수 해제
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
        Intent intent = new Intent(getActivity(), com.example.hwhan.rrealfinal.Recommend_crop.class);
        intent.putExtra("locate",locate);
        startActivity(intent);

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {

    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }
    @Override
    public void onBack() {
        MainActivity activity = (MainActivity)getActivity();
        activity.setOnBackPressedListener(null);
        activity.onBackPressed();
    }
    @Override
    public void onAttach(Activity context){
        super.onAttach(context);
        ((MainActivity)context).setOnBackPressedListener(this);
    }
}
