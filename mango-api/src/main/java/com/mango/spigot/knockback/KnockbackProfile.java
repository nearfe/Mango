package com.mango.spigot.knockback;

public interface KnockbackProfile {

    String getName();

    String[] getValues();

    double getHorizontal();

    void setHorizontal(double horizontal);

    double getVertical();

    void setVertical(double vertical);

    double getVerticalLimit();

    void setVerticalLimit(double verticalLimit);

    double getExtraHorizontal();

    void setExtraHorizontal(double extraHorizontal);

    double getExtraVertical();

    void setExtraVertical(double extraVertical);

    double getHorizontalFriction();

    void setHorizontalFriction(double horizontalFriction);

    double getVerticalFriction();

    void setVerticalFriction(double verticalFriction);

    double getRangeReduction();

    void setRangeReduction(double rangeReduction);

    double getRangeReductionStart();

    void setRangeReductionStart(double rangeReductionStart);

    double getRangeReductionLimit();

    void setRangeReductionLimit(double rangeRedutionLimit);

    boolean isComboMode();

    void setComboMode(boolean comboMode);

    double getComboHeight();

    void setComboHeight(double comboHeight);

    int getComboTicks();

    void setComboTicks(int comboTicks);

    double getComboVelocity();

    void setComboVelocity(double comboVelocity);

    void save();

}
