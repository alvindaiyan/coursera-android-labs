package modernartui.com.mordernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by Yan on 4/24/2015.
 */
public class MoreInformationDialog extends DialogFragment {

    public static MoreInformationDialog newInstance()
    {
        return new MoreInformationDialog();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.message)
                .setPositiveButton(R.string.dialog_visit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
                        startActivity(browserIntent);
                    }
                })
                .setNegativeButton(R.string.dialog_not_now, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        MoreInformationDialog.this.dismiss();
                    }
                });
        // Create the AlertDialog object and return it

        return builder.create();
    }
}
