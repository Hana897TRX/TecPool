package mx.tec.lumaapp

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.gms.maps.SupportMapFragment
import mx.tec.lumaapp.elementos_recycler.adapter.RutaAdapter
import mx.tec.lumaapp.elementos_recycler.model.RutaModel

class RidesFragment : Fragment() , android.location.LocationListener {
    lateinit var locationManager : LocationManager
    var LOCATION_CODE = 102

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.rides_layout, container, false)

        val rides_mostrados = arrayListOf<RutaModel>(
            RutaModel("Buena Vista", "Tec CVA", 40.00f, "9:00 am"),
            RutaModel("Temixco Centro", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Tec CVA", "Galerias CVA", 80.00f, "4:00 pm"),
            RutaModel("Buena Vistadddddddddddddddddd", "Tec CVA", 40.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am")
        )

        val adapter = RutaAdapter(this.requireContext(), R.layout.rutas_layout, rides_mostrados)
        val rV = view.findViewById<RecyclerView>(R.id.recycler_rides)

        rV.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        rV.setHasFixedSize(true)
        rV.adapter = adapter

        return view
    }

    fun Mostrar_RutaPopUp(view: View){
        val dialog = Dialog(this.requireContext())
        dialog.setContentView(R.layout.info_ruta_pop)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        verifyLocationPermission(context as Activity, Manifest.permission.ACCESS_FINE_LOCATION, LOCATION_CODE)
        val mapFragment = parentFragmentManager.findFragmentById(R.id.mapx) as SupportMapFragment

        mapFragment.getMapAsync{googleMap ->
            if (ActivityCompat.checkSelfPermission(context as Activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context as Activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                googleMap.isMyLocationEnabled = true
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun verifyLocationPermission(context : Activity, permission : String ,code : Int){
        if(ContextCompat.checkSelfPermission(context, permission) !=
            PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(context, permission)){
                val builder = AlertDialog.Builder(context)
                builder.setMessage(R.string.location_permission)
                    .setTitle(R.string.permission_needed)
                    .setPositiveButton(R.string.accept){_, _->
                        ActivityCompat.requestPermissions(context,
                            arrayOf(permission), code)
                    }
                    .show()
            }
            else{
                ActivityCompat.requestPermissions(context,
                    arrayOf(permission), code)
            }
        }
        else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            10 -> {
                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    //Cerrar la app
                }
                else{
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
                }
            }
        }
    }

    override fun onLocationChanged(location: Location) {

    }
}
