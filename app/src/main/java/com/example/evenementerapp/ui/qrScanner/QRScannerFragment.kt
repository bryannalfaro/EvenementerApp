package com.example.evenementerapp.ui.qrScanner

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.evenementerapp.R
import com.google.zxing.integration.android.IntentIntegrator



class QRScannerFragment : Fragment() {

    /**
     * Referencias:
     * https://stackoverflow.com/questions/40725336/android-studio-start-qr-code-scanner-from-fragment
     * https://www.youtube.com/watch?v=rEHSdvLjU4I
     */


    internal var btnScan: Button? = null
    internal var qrScanIntegrator: IntentIntegrator? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_qr_scanner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnScan = view.findViewById(R.id.btnScan)
        btnScan!!.setOnClickListener { Accion() }

        qrScanIntegrator = IntentIntegrator.forSupportFragment(this)
        qrScanIntegrator?.setOrientationLocked(false)


    }

    private fun Accion() {
        qrScanIntegrator?.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(activity, "Cancelado", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(activity, "Texto Escaneado: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
