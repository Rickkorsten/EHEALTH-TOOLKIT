    package link.fls.swipestacksample;

    import android.content.Context;
    import android.content.SharedPreferences;

    /**
     * Created by rickk on 11-4-2017.
     */

    public class PrefManager {
        private SharedPreferences pref;
        private SharedPreferences.Editor editor;

        // Shared preferences file name
        private static final String PREF_NAME = "E-health";

        private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

        public PrefManager(Context context) {
            int PRIVATE_MODE = 0;
            pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = pref.edit();
        }

        public void setFirstTimeLaunch(boolean isFirstTime) {
            editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
            editor.commit();
        }

        public boolean isFirstTimeLaunch() {
            return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
        }

    }