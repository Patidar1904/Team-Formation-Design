package com.ext.teamformation.Utils;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.jksiezni.permissive.PermissionsGrantedListener;
import com.github.jksiezni.permissive.PermissionsRefusedListener;
import com.github.jksiezni.permissive.Permissive;
import com.kbeanie.imagechooser.api.ChooserType;
import com.kbeanie.imagechooser.api.ChosenFile;
import com.kbeanie.imagechooser.api.ChosenImage;
import com.kbeanie.imagechooser.api.ChosenImages;
import com.kbeanie.imagechooser.api.FileChooserListener;
import com.kbeanie.imagechooser.api.FileChooserManager;
import com.kbeanie.imagechooser.api.ImageChooserListener;
import com.kbeanie.imagechooser.api.ImageChooserManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public abstract class FileManagerUtill extends AppCompatActivity implements ImageChooserListener, FileChooserListener {


    private final static String TAG = "FileManagerUtill";
    private String filePath;
    private int chooserType;
    private ImageChooserManager imageChooserManager;
    private FileChooserManager fm;
    private String originalFilePath;
    private String thumbnailFilePath;
    private String thumbnailSmallFilePath;
    Boolean isCrop = false;
    private boolean isActivityResultOver = false;

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }

    public abstract Void OnImageChosanResponce(String FileExt, String Path, String Base64);

    public abstract Void OnFileChosan(ChosenFile file, String Base64);

    public abstract Void OnImageFromCrop(String filePath, String Base64);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //  getTheme().applyStyle(new Preferences(this).getFontStyle().getResId(), true);


        super.onCreate(savedInstanceState);
    }

    public void chooseFile() {
        fm = new FileChooserManager(this, true);
        Bundle bundle = new Bundle();
        bundle.putBoolean(Intent.EXTRA_ALLOW_MULTIPLE, true);
        fm.setExtras(bundle);
        fm.setFileChooserListener(this);
        fm.clearOldFiles();
        try {
            // pbar.setVisibility(View.VISIBLE);
            filePath = fm.choose();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception__", e.getMessage() + "__");
        }

    }

    public void chooseImage() {
        isCrop = false;
        new Permissive.Request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .whenPermissionsGranted(new PermissionsGrantedListener() {
                    @Override
                    public void onPermissionsGranted(String[] permissions) throws SecurityException {
                        chooserType = ChooserType.REQUEST_PICK_PICTURE;
                        imageChooserManager = new ImageChooserManager(FileManagerUtill.this,
                                ChooserType.REQUEST_PICK_PICTURE, true);
                        Bundle bundle = new Bundle();
                        bundle.putBoolean(Intent.EXTRA_ALLOW_MULTIPLE, false);
                        imageChooserManager.setExtras(bundle);
                        imageChooserManager.setImageChooserListener(FileManagerUtill.this);
                        imageChooserManager.clearOldFiles();
                        try {
                            // pbar.setVisibility(View.VISIBLE);
                            filePath = imageChooserManager.choose();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .whenPermissionsRefused(new PermissionsRefusedListener() {
                    @Override
                    public void onPermissionsRefused(String[] permissions) {

                    }
                })
                .execute(this);
    }


    public void chooseImage_Crop() {

        isCrop = true;

        new Permissive.Request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .whenPermissionsGranted(new PermissionsGrantedListener() {
                    @Override
                    public void onPermissionsGranted(String[] permissions) throws SecurityException {
                        chooserType = ChooserType.REQUEST_PICK_PICTURE;
                        imageChooserManager = new ImageChooserManager(FileManagerUtill.this,
                                ChooserType.REQUEST_PICK_PICTURE, true);
                        Bundle bundle = new Bundle();
                        bundle.putBoolean(Intent.EXTRA_ALLOW_MULTIPLE, false);
                        imageChooserManager.setExtras(bundle);
                        imageChooserManager.setImageChooserListener(FileManagerUtill.this);
                        imageChooserManager.clearOldFiles();
                        try {
                            // pbar.setVisibility(View.VISIBLE);
                            filePath = imageChooserManager.choose();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .whenPermissionsRefused(new PermissionsRefusedListener() {
                    @Override
                    public void onPermissionsRefused(String[] permissions) {

                    }
                })
                .execute(this);
    }

    public void takePicture() {
        isCrop = false;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        new Permissive.Request(Manifest.permission.CAMERA)
                .whenPermissionsGranted(new PermissionsGrantedListener() {
                    @Override
                    public void onPermissionsGranted(String[] permissions) throws SecurityException {
                        chooserType = ChooserType.REQUEST_CAPTURE_PICTURE;
                        imageChooserManager = new ImageChooserManager(FileManagerUtill.this,
                                ChooserType.REQUEST_CAPTURE_PICTURE, true);
                        imageChooserManager.setImageChooserListener(FileManagerUtill.this);
                        try {
//pbar.setVisibility(View.VISIBLE);
                            filePath = imageChooserManager.choose();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                            Log.e("error1___", e.getMessage() + "__");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("error2___", e.getMessage() + "__");
                        }
                    }
                })
                .whenPermissionsRefused(new PermissionsRefusedListener() {
                    @Override
                    public void onPermissionsRefused(String[] permissions) {
// requestPermission();
                        Log.e("permissions__", permissions[0] + "__");
                    }
                })
                .execute(this);
    }

    public void takePicture_Crop() {
        isCrop = true;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        new Permissive.Request(Manifest.permission.CAMERA)
                .whenPermissionsGranted(new PermissionsGrantedListener() {
                    @Override
                    public void onPermissionsGranted(String[] permissions) throws SecurityException {
                        chooserType = ChooserType.REQUEST_CAPTURE_PICTURE;
                        imageChooserManager = new ImageChooserManager(FileManagerUtill.this,
                                ChooserType.REQUEST_CAPTURE_PICTURE, true);
                        imageChooserManager.setImageChooserListener(FileManagerUtill.this);
                        try {
//pbar.setVisibility(View.VISIBLE);
                            filePath = imageChooserManager.choose();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                            Log.e("error1___", e.getMessage() + "__");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("error2___", e.getMessage() + "__");
                        }
                    }
                })
                .whenPermissionsRefused(new PermissionsRefusedListener() {
                    @Override
                    public void onPermissionsRefused(String[] permissions) {
// requestPermission();
                        Log.e("permissions__", permissions[0] + "__");
                    }
                })
                .execute(this);
    }

    @Override
    public void onImageChosen(ChosenImage image) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Log.i(TAG, "Chosen Image: O - " + image.getFilePathOriginal());
                Log.i(TAG, "Chosen Image: T - " + image.getFileThumbnail());
                Log.i(TAG, "Chosen Image: Ts - " + image.getFileThumbnailSmall());
                isActivityResultOver = true;
                originalFilePath = image.getFilePathOriginal();
                thumbnailFilePath = image.getFileThumbnail();

                thumbnailSmallFilePath = image.getFileThumbnailSmall();
                // pbar.setVisibility(View.GONE);
                if (image != null) {

                    String Base64 = "";
                    try {

                        Base64 = encodeFileToBase64Binary(image.getFilePathOriginal());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    if (isCrop) {
                        /*CropImage.activity(Uri.fromFile(new File(image.getFilePathOriginal())))
                                .setAspectRatio(5, 4)
                                .start(FileManagerUtill.this);*/
                    } else {
                        OnImageChosanResponce(image.getExtension(), image.getFilePathOriginal(), Base64);
                    }


                  /*  CropImage.activity(Uri.fromFile(new File(image.getFilePathOriginal())))
                            .setAspectRatio(3, 2)
                            .start(FileManagerUtill.this);*/
                    //  OnImageChosanResponce(image.getExtension(), image.getFilePathOriginal(), Base64);
                } else {
                    Log.i(TAG, "Chosen Image: Is null");
                }


            }
        });

    }

    public void loadImage(ImageView iv, final String path) {

/*
        Picasso.with(FileManagerUtill.this)
                .load(Uri.fromFile(new File(path)))
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .fit()
                .centerInside()
                .into(iv, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.i(TAG, "Picasso Success Loading Thumbnail - " + path);
                    }

                    @Override
                    public void onError() {
                        Log.i(TAG, "Picasso Error Loading Thumbnail Small - " + path);
                    }
                });*/

    }

    public void loadImage_From_Server(ImageView iv, final String path, int image_error) {

        if (path.equalsIgnoreCase("")) {

        } else {
         /*   Picasso.with(this)
                    .load(path)
                    .placeholder(image_error)
                    .error(image_error)
                    .into(iv, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.i("TAG", "Picasso Success Loading Thumbnail - " + path);
                        }

                        @Override
                        public void onError() {
                            Log.i("TAG", "Picasso Error Loading Thumbnail Small - " + path);
                        }
                    });*/
        }
    }

    @Override
    public void onFileChosen(ChosenFile chosenFile) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String Base64 = "";
                try {
                    Base64 = encodeFileToBase64Binary(chosenFile.getFilePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                OnFileChosan(chosenFile, Base64);
                //populateFileDetails(chosenFile);
            }
        });
    }

    public void pickFile(View view) {
        fm = new FileChooserManager(this);
        fm.setFileChooserListener(this);
        try {

            fm.choose();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void populateFileDetails(ChosenFile file) {
        Log.e(TAG, "File name: " + file.getFileName() + "\n\n");
        Log.e(TAG, "File path: " + file.getFilePath() + "\n\n");
        Log.e(TAG, "Mime type: " + file.getMimeType() + "\n\n");
        Log.e(TAG, "File extn: " + file.getExtension() + "\n\n");
        Log.e(TAG, "File size: " + file.getFileSize() / 1024 + "KB");
        //textViewFileDetails.setText(text.toString());
    }

    @Override
    public void onError(String s) {

    }

    @Override
    public void onImagesChosen(ChosenImages chosenImages) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "OnActivityResult");
        Log.i(TAG, "File Path : " + filePath);
        Log.i(TAG, "Chooser Type: " + chooserType);
        /*if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();


                Log.e("resultUri", resultUri + "___");

                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(resultUri);

                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    Log.e("ResultBase64", encodeImage(selectedImage) + "___");


                    String Base64 = encodeImage(selectedImage);

                    String path = resultUri.getPath();


                    OnImageFromCrop(path, Base64);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        } else*/
        if (resultCode == RESULT_OK && (requestCode == ChooserType.REQUEST_PICK_PICTURE || requestCode == ChooserType.REQUEST_CAPTURE_PICTURE)) {


            if (imageChooserManager == null) {
                reinitializeImageChooser();
            }
            imageChooserManager.submit(requestCode, data);

            Log.e("DataResponse", data + "_____");

        } else if (requestCode == ChooserType.REQUEST_PICK_FILE && resultCode == RESULT_OK) {
            Log.e("*************", "---");
            if (fm == null) {
                fm = new FileChooserManager(this);
                fm.setFileChooserListener(this);
            }
            Log.i(TAG, "Probable file size: " + fm.queryProbableFileSize(data.getData(), FileManagerUtill.this));
            fm.submit(requestCode, data);
        } else {
            // pbar.setVisibility(View.GONE);
        }
    }

    private void reinitializeImageChooser() {
        imageChooserManager = new ImageChooserManager(FileManagerUtill.this, chooserType, true);
        Bundle bundle = new Bundle();
        bundle.putBoolean(Intent.EXTRA_ALLOW_MULTIPLE, true);
        imageChooserManager.setExtras(bundle);
        imageChooserManager.setImageChooserListener(FileManagerUtill.this);
        imageChooserManager.reinitialize(filePath);
    }


    public String resizeBase64Image(String base64image) {
        byte[] encodeByte = Base64.decode(base64image.getBytes(), Base64.DEFAULT);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        Bitmap image = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length, options);


        if (image.getHeight() <= 400 && image.getWidth() <= 400) {
            return base64image;
        }
        image = Bitmap.createScaledBitmap(image, 400, 400, false);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);

        byte[] b = baos.toByteArray();
        System.gc();
        return Base64.encodeToString(b, Base64.NO_WRAP);

    }


    public String BitMapToBase64String(Bitmap bitmap) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] b = baos.toByteArray();

        String temp = null;

        try {

            System.gc();

            temp = Base64.encodeToString(b, Base64.DEFAULT);

        } catch (Exception e) {

            e.printStackTrace();

        } catch (OutOfMemoryError e) {

            baos = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            b = baos.toByteArray();

            temp = Base64.encodeToString(b, Base64.DEFAULT);

            Log.e("EWN", "Out of memory error catched");

        }

        return temp;

    }


    private String encodeFileToBase64Binary(String fileName)
            throws IOException {

        File file = new File(fileName);
        byte[] bytes = loadFile(file);

        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }


    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

  /*  private String encodeFileToBase64Binary(String fileName)
            throws IOException {

        File file = new File(fileName);
        byte[] bytes = loadFile(file);
        byte[] encoded = Base64.encode(bytes, 1);
        String encodedString = new String(encoded);

        //Log.e("Base64",encodedString);
        return encodedString;
    }*/


}
