package mx.tec.lumaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val bottomNavigationView = this.activity?.findViewById<BottomNavigationView>(R.id.nav_bar)
//        bottomNavigationView!!.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.ecoActivity -> {
//                    findNavController().navigate(R.id.action_homeActivity_to_ecoActivity)
//                    true
//                }
//                R.id.perfilActivity -> {
//                    findNavController().navigate(R.id.action_homeActivity_to_perfilActivity)
//                    true
//                }
//                R.id.ridesActivity -> {
//                    findNavController().navigate(R.id.action_homeActivity_to_ridesActivity)
//                    true
//                }
//                else -> false
//            }
//        }
    }
}