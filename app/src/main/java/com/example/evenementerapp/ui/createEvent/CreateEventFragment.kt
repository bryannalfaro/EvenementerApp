package com.example.evenementerapp.ui.createEvent

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.evenementerapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import java.io.*
import java.lang.Exception
import java.util.*

/**
 * Referencias:
 * https://medium.com/@hasangi/capture-image-or-choose-from-gallery-photos-implementation-for-android-a5ca59bc6883
 * https://stackoverflow.com/questions/10165302/dialog-to-pick-image-from-gallery-or-from-camera
 * https://androidwave.com/capture-image-from-camera-gallery/
 */

@Suppress("DEPRECATION")
class CreateEventFragment : Fragment() {



    var btn: Button?=null
    var floatingButton:FloatingActionButton?=null
    private var imageview: ImageView? = null
    private var imageView2:ImageView?=null
    internal var bitmap: Bitmap? = null
    var editText:EditText?=null
    private val GALLERY = 1
    private val CAMERA = 2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn=view.findViewById(R.id.save)
        editText=view.findViewById(R.id.eventName)
        imageView2=view.findViewById(R.id.imageView2)

        imageview=view!!.findViewById(R.id.imageView)
        floatingButton=view.findViewById(R.id.floatingActionButton)


        floatingButton!!.setOnClickListener {
            mensajeImagenMostrar()
        }

        btn!!.setOnClickListener {
            if (editText!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity, "Por favor ingrese un texto!", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    bitmap = PassTexttoImage(editText!!.text.toString())
                    imageView2!!.setImageBitmap(bitmap)

                    Toast.makeText(activity, "QRCode guardado en -> ", Toast.LENGTH_SHORT).show()
                } catch (e: WriterException) {
                    e.printStackTrace()
                }

            }

            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                if(ContextCompat.checkSelfPermission(requireActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),100)
                }else{
                    guardarImagenAlmacenamiento(bitmap)
                }
            } else{
                guardarImagenAlmacenamiento(bitmap)
            }
        }

    }

    fun guardarImagenAlmacenamiento(bitmap: Bitmap?){
        val externalStorageState= Environment.getExternalStorageState()
        if(externalStorageState.equals(Environment.MEDIA_MOUNTED)){
            val storageDirectory=Environment.getExternalStorageDirectory().toString()
            val file = File(storageDirectory,"${editText!!.text.toString()}.jpg")
            try {
                val stream:OutputStream=FileOutputStream(file)
                bitmap!!.compress(Bitmap.CompressFormat.JPEG,100,stream)
                stream.flush()
                stream.close()
                Toast.makeText(activity,"${storageDirectory.toString()}",Toast.LENGTH_SHORT).show()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }else{

            Toast.makeText(activity,"No",Toast.LENGTH_SHORT).show()

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode==100){
            if(grantResults.isNotEmpty()&& grantResults[0]==PackageManager.PERMISSION_GRANTED){

            }else{
                Toast.makeText(activity,"No hay permiso", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @Throws(WriterException::class)
    private fun PassTexttoImage(Value: String): Bitmap? {
        val bitMatrix: BitMatrix
        try {
            bitMatrix = MultiFormatWriter().encode(
                Value,
                BarcodeFormat.QR_CODE,

                QRcodeWidth, QRcodeWidth, null
            )

        } catch (Illegalargumentexception: IllegalArgumentException) {

            return null
        }

        val bitMatrixWidth = bitMatrix.width

        val bitMatrixHeight = bitMatrix.height

        val pixels = IntArray(bitMatrixWidth * bitMatrixHeight)

        for (y in 0 until bitMatrixHeight) {
            val offset = y * bitMatrixWidth

            for (x in 0 until bitMatrixWidth) {

                pixels[offset + x] = if (bitMatrix.get(x, y))
                    resources.getColor(R.color.black)
                else
                    resources.getColor(R.color.white)
            }
        }
        val bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444)

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight)
        return bitmap
    }

    private fun mensajeImagenMostrar() {
        val pictureDialog = AlertDialog.Builder(activity)
        pictureDialog.setTitle("Selecciona una opcion")
        val pictureDialogItems = arrayOf("Seleccionar foto de galeria", "Capturar de la camara")
        pictureDialog.setItems(pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> PhotoGallery()
                1 -> PhotoCamera()
            }
        }
        pictureDialog.show()
    }

    fun PhotoGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK,

            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun PhotoCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    public override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY)
        {
            if (data != null)
            {
                val contentURI = data!!.data
                try
                {
                    val bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, contentURI)
                    val path = saveImage2(bitmap)
                    Toast.makeText(activity, "Imagen guardada!", Toast.LENGTH_SHORT).show()
                    imageview!!.setImageBitmap(bitmap)

                }
                catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(activity, "Failed!", Toast.LENGTH_SHORT).show()
                }

            }

        }
        else if (requestCode == CAMERA)
        {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            imageview!!.setImageBitmap(thumbnail)
            saveImage2(thumbnail)
            Toast.makeText(activity, "Imagen guardada!", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveImage2(myBitmap: Bitmap):String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
            (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORYA)


        if (!wallpaperDirectory.exists())
        {

            wallpaperDirectory.mkdirs()
        }

        try
        {

            val f = File(wallpaperDirectory, ((Calendar.getInstance()
                .getTimeInMillis()).toString() + ".jpg"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(activity,
                arrayOf(f.getPath()),
                arrayOf("image/jpeg"), null)
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.absolutePath)

            return f.getAbsolutePath()
        }
        catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }

    companion object {
        val QRcodeWidth = 500
        private val IMAGE_DIRECTORYA = "/demonuts"
    }



}
