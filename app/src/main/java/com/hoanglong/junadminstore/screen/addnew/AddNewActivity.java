package com.hoanglong.junadminstore.screen.addnew;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.hoanglong.junadminstore.R;
import com.hoanglong.junadminstore.base.BaseActivity;
import com.hoanglong.junadminstore.data.model.phone_product.DetailContent;
import com.hoanglong.junadminstore.data.model.phone_product.ItemPhoneProduct;
import com.hoanglong.junadminstore.data.model.phone_product.Parameter;
import com.hoanglong.junadminstore.screen.addnew.adapter.AddContentAdapter;
import com.hoanglong.junadminstore.screen.addnew.adapter.AddInfoAdapter;
import com.hoanglong.junadminstore.screen.addnew.adapter.AddSaleAdapter;
import com.hoanglong.junadminstore.screen.addnew.adapter.AddSliderAdapter;
import com.hoanglong.junadminstore.service.BaseService;
import com.hoanglong.junadminstore.utils.Constant;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewActivity extends BaseActivity implements View.OnClickListener {

    private static final int IMG_REQUEST = 1;
    private static final int TAKE_PHOTO_CODE = 2;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 3;
    private static final String DOMAIN = "MayBE";

    @BindView(R.id.ic_back)
    ImageView mImageBack;
    @BindView(R.id.relative_choose_image)
    RelativeLayout mRelativeChooseImage;
    @BindView(R.id.choose_image)
    ImageView mImageChoose;
    @BindView(R.id.image_item)
    ImageView mImageItem;
    @BindView(R.id.button_upload)
    Button mButtonUpload;
    @BindView(R.id.add_sale)
    ImageView mImageAddSale;
    @BindView(R.id.recycler_add_sale)
    RecyclerView mRecyclerAddSale;
    @BindView(R.id.et_sale)
    EditText mEditTextSale;
    @BindView(R.id.et_title_info)
    EditText mEditTextTitleInfo;
    @BindView(R.id.et_info)
    EditText mEditInfo;
    @BindView(R.id.recycler_info_item)
    RecyclerView mRecyclerInfo;
    @BindView(R.id.add_list_info)
    ImageView mImageAddInfo;
    @BindView(R.id.recycler_slider)
    RecyclerView mRecyclerSlider;
    @BindView(R.id.relative_add_slider)
    RelativeLayout mRelativeSlider;
    @BindView(R.id.et_description)
    EditText mEditTextContent;
    @BindView(R.id.image_description)
    ImageView mImageChooseContent;
    @BindView(R.id.add_description)
    ImageView mImageAddContent;
    @BindView(R.id.recycler_description)
    RecyclerView mRecyclerDes;
    @BindView(R.id.et_name)
    EditText mEditTextNameProduct;
    @BindView(R.id.tv_categories)
    TextView mTextCategories;
    @BindView(R.id.cb_phone)
    CheckBox mCheckPhone;
    @BindView(R.id.cb_tablet)
    CheckBox mCheckTablet;
    @BindView(R.id.cb_laptop)
    CheckBox mCheckBoxLaptop;
    @BindView(R.id.cb_accession)
    CheckBox mCheckBoxAccession;
    @BindView(R.id.cb_watch)
    CheckBox mCheckBoxWatch;
    @BindView(R.id.et_price_item)
    EditText mEditTextPrice;
    @BindView(R.id.et_link_video)
    EditText mEditTextLinkVideo;
    @BindView(R.id.et_title_des)
    EditText mEditTextHeaderTitle;


    private Uri mPath;
    boolean mIsGallery = true;
    private Bitmap mBitmap;
    private String mNameOfImage;
    private boolean mSuccess;
    private ProgressDialog mProgressDialog;
    private List<String> mSales = new ArrayList<>();
    private List<Parameter> mParameters = new ArrayList<>();
    private List<Bitmap> mBitmaps = new ArrayList<>();
    private List<String> mImageSlider = new ArrayList<>();
    private List<DetailContent> mDetailContents = new ArrayList<>();
    private AddSaleAdapter mAddSaleAdapter;
    private AddInfoAdapter mAddInfoAdapter;
    private AddSliderAdapter mAddSliderAdapter;
    private AddContentAdapter mAddContentAdapter;
    private ImageView mImageOther;
    private boolean isImageMain;
    private boolean isImageSlider;
    private boolean isImageContent;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_add_new;
    }

    @Override
    protected void initComponent() {
        ButterKnife.bind(this);
        initRecyclerSale();
        checkBox();
        mImageBack.setOnClickListener(this);
        mRelativeChooseImage.setOnClickListener(this);
        mButtonUpload.setOnClickListener(this);
        mImageAddSale.setOnClickListener(this);
        mImageAddInfo.setOnClickListener(this);
        mRelativeSlider.setOnClickListener(this);
        mImageChooseContent.setOnClickListener(this);
        mImageAddContent.setOnClickListener(this);
    }

    private void initRecyclerSale() {
        mAddSaleAdapter = new AddSaleAdapter(mSales);
        mRecyclerAddSale.setAdapter(mAddSaleAdapter);

        mAddInfoAdapter = new AddInfoAdapter(mParameters);
        mRecyclerInfo.setAdapter(mAddInfoAdapter);

        mAddSliderAdapter = new AddSliderAdapter(mBitmaps);
        mRecyclerSlider.setAdapter(mAddSliderAdapter);

        mAddContentAdapter = new AddContentAdapter(mDetailContents);
        mRecyclerDes.setAdapter(mAddContentAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ic_back:
                onBackPressed();
                break;
            case R.id.relative_choose_image:
                isImageMain = true;
                mImageOther = mImageItem;
                chooseImage();
                break;
            case R.id.button_upload:
                showProgress();
                inValid();
                break;
            case R.id.add_sale:
                addSale();
            case R.id.add_list_info:
                addInfo();
                break;
            case R.id.relative_add_slider:
                isImageMain = false;
                isImageSlider = true;
                chooseImage();
                break;
            case R.id.image_description:
                isImageMain = false;
                isImageSlider = false;
                isImageContent = true;
                chooseImage();
                break;
            case R.id.add_description:
                addDetailContent();
                break;
        }
    }

    private void upLoadNewProduct() {
        String type = "null";
        String typeCategory = mTextCategories.getText().toString();
        String title = mEditTextNameProduct.getText().toString();

        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        String price = fmt.format(Long.valueOf(mEditTextPrice.getText().toString()));

        String image = "https://res.cloudinary.com/hoanglongb/image/upload/v1544258437/" + mNameOfImage + ".jpg";
        Integer rating = 0;
        String numberRating = "0 đánh giá";
        String titleH2 = "";
        String titleContent = mEditTextHeaderTitle.getText().toString();
        List<String> slider = mImageSlider;
        List<DetailContent> detailContent = mDetailContents;
        List<Parameter> parameter = mParameters;
        List<String> listSale = mSales;
        String linkVideo = mEditTextLinkVideo.getText().toString();


        ItemPhoneProduct newProduct = new ItemPhoneProduct(
                type, typeCategory, title, price, image, rating, numberRating,
                titleH2, titleContent, slider, detailContent, parameter, listSale, linkVideo
        );

        Call<ItemPhoneProduct> upload = BaseService.getService().uploadNewProduct(newProduct);
        upload.enqueue(new Callback<ItemPhoneProduct>() {
            @Override
            public void onResponse(@NonNull Call<ItemPhoneProduct> call, @NonNull Response<ItemPhoneProduct> response) {
                Toast.makeText(AddNewActivity.this, "Upload Success", Toast.LENGTH_SHORT).show();
                hideProgress();
            }

            @Override
            public void onFailure(@NonNull Call<ItemPhoneProduct> call, @NonNull Throwable t) {
                Toast.makeText(AddNewActivity.this, "Upload Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void inValid() {
        if (mEditTextNameProduct.getText().toString().isEmpty()
                || mBitmap == null
                || mImageSlider.size() == 0
                || mParameters.size() == 0
                || mEditTextPrice.getText().toString().isEmpty()
                || mSales.size() == 0
                || mDetailContents.size() == 0) {
            Toast.makeText(this, "Bạn không được để trống các trường có dấu (*) ", Toast.LENGTH_SHORT).show();
        } else {
            upLoadNewProduct();
        }
    }

    private void addDetailContent() {
        String imageShow = "https://res.cloudinary.com/hoanglongb/image/upload/v1544258437/" + mNameOfImage + ".jpg";
        if (!mEditTextContent.getText().toString().isEmpty()) {
            DetailContent detailContent = new DetailContent(mEditTextContent.getText().toString(), imageShow);
            mDetailContents.add(detailContent);
            mAddContentAdapter.notifyDataSetChanged();
        }
        mEditTextContent.setText("");
        mImageChooseContent.setImageResource(R.drawable.ic_add_a_photo_black_24dp);
    }

    private void addInfo() {
        Parameter parameter = new Parameter(mEditTextTitleInfo.getText().toString(),
                mEditInfo.getText().toString());
        mParameters.add(parameter);
        mAddInfoAdapter.notifyDataSetChanged();
        mEditTextTitleInfo.setText("");
        mEditInfo.setText("");
    }

    private void addSale() {
        String sale = mEditTextSale.getText().toString();
        mSales.add(sale);
        mAddSaleAdapter.notifyDataSetChanged();
        mEditTextSale.setText("");
    }

    private void chooseImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddNewActivity.this);
        builder.setTitle("Choose Image Source");
        builder.setItems(new CharSequence[]{"Gallery", "Camera"},
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(intent, IMG_REQUEST);
                                break;
                            case 1:
                                showCameraPreview();
                                break;
                        }
                    }
                });

        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA) {
            // Request for camera permission.
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera();
            } else {
                // Permission request was denied.
                Toast.makeText(this, "camera_permission_denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_PHOTO_CODE);
    }

    private void showCameraPreview() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            // Permission is already available, start camera preview
            startCamera();
        } else {
            // Permission is missing and must be requested.
            requestCameraPermission();
        }
    }

    private void requestCameraPermission() {
        // Permission has not been granted and must be requested.
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {

            ActivityCompat.requestPermissions(AddNewActivity.this,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);

        } else {
            // Request the permission. The result will be received in onRequestPermissionResult().
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
            showProgress();
            mPath = data.getData();
            try {
                mBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mPath);
                if (isImageMain) {
                    new UploadImage().execute();
                    mImageOther.setImageBitmap(mBitmap);
                    mImageChoose.setVisibility(View.GONE);
                } else if (isImageSlider) {
                    new UploadImage().execute();
                    mBitmaps.add(mBitmap);
                    mAddSliderAdapter.notifyDataSetChanged();
                } else if (isImageContent) {
                    new UploadImage().execute();
                    mImageChooseContent.setImageBitmap(mBitmap);
                }
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            mIsGallery = true;
        }
        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK && data != null) {
            showProgress();
            mBitmap = (Bitmap) data.getExtras().get("data");
            if (isImageMain) {
                mImageOther.setImageBitmap(mBitmap);
                mImageChoose.setVisibility(View.GONE);
            } else if (isImageSlider) {
                new UploadImage().execute();
                mBitmaps.add(mBitmap);
                mAddSliderAdapter.notifyDataSetChanged();
            } else if (isImageContent) {
                new UploadImage().execute();
                mImageChooseContent.setImageBitmap(mBitmap);
            }
            mIsGallery = false;
        }
    }

    private void uploadImage() {
        mSuccess = false;
        Map config = new HashMap();
        config.put("cloud_name", Constant.Cloudinary.CLOUD_NAME);
        config.put("api_key", Constant.Cloudinary.API_KEY);
        config.put("api_secret", Constant.Cloudinary.SECRET_KEY);
        Cloudinary cloudinary = new Cloudinary(Constant.Cloudinary.URL);
        //TODO: id fb of user
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        Date currentTime = Calendar.getInstance().getTime();
        String time = calendar.get(Calendar.YEAR) + "" + calendar.get(Calendar.MONTH) + "" + calendar.get(Calendar.DATE)
                + "" + currentTime.getHours() + "" + currentTime.getMinutes() + "" + currentTime.getSeconds();
        mNameOfImage = DOMAIN + time;
        if (isImageSlider) {
            String imageShow = "https://res.cloudinary.com/hoanglongb/image/upload/v1544258437/" + mNameOfImage + ".jpg";
            mImageSlider.add(imageShow);
        }
        cloudinary.url()
                .transformation(new Transformation().width(300).crop("fill"))
                .generate(mNameOfImage + ".jpg");
        try {

            InputStream inputStream;
            if (mIsGallery) {
                inputStream = getContentResolver().openInputStream(mPath);
                cloudinary.uploader().upload(inputStream, ObjectUtils.asMap("public_id", mNameOfImage));
            } else {
                String myBase64Image = encodeToBase64(mBitmap);
                cloudinary.uploader().upload(myBase64Image, ObjectUtils.asMap("public_id", mNameOfImage));

            }
            mSuccess = true;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgress();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String encodeToBase64(Bitmap image) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return "data:image/png;base64," + Base64.encodeToString(byteArray, Base64.DEFAULT);
    }


    class UploadImage extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            uploadImage();
            return null;
        }
    }

    public void showProgress() {
        if (mProgressDialog != null) {
            return;
        }
        mProgressDialog = new ProgressDialog(AddNewActivity.this);
        mProgressDialog.setMessage("Uploading...");
        mProgressDialog.show();
    }

    public void hideProgress() {
        if (mProgressDialog == null)
            return;
        mProgressDialog.dismiss();
        mProgressDialog = null;
    }

    public void checkBox() {

        mCheckPhone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                if (isCheck) {
                    mTextCategories.setText("Điện thoại ");
                }
            }
        });

        mCheckTablet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                if (isCheck) {
                    mTextCategories.setText("Tablet");
                }
            }
        });

        mCheckBoxLaptop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                if (isCheck) {
                    mTextCategories.setText("Laptop");
                }
            }
        });

        mCheckBoxAccession.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                if (isCheck) {
                    mTextCategories.setText("Phụ kiện");
                }
            }
        });

        mCheckBoxWatch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                if (isCheck) {
                    mTextCategories.setText("Đồng hồ");
                }
            }
        });

    }
}
