package com.kickstarter.models;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.kickstarter.R;
import com.kickstarter.libs.KSColorUtils;
import com.kickstarter.libs.ViewUtils;
import com.kickstarter.libs.qualifiers.AutoGson;

import auto.parcel.AutoParcel;

@AutoParcel
@AutoGson
abstract public class Category implements Parcelable {
  public abstract int color();
  public abstract long id();
  public abstract String name();
  @Nullable public abstract Category parent();
  @Nullable public abstract Long parentId();
  public abstract int position();
  @Nullable public abstract Integer projectsCount();
  public abstract String slug();

  @AutoParcel.Builder
  public abstract static class Builder {
    public abstract Builder color(int __);
    public abstract Builder id(long __);
    public abstract Builder name(String __);
    public abstract Builder parent(Category __);
    public abstract Builder parentId(Long __);
    public abstract Builder position(int __);
    public abstract Builder projectsCount(Integer __);
    public abstract Builder slug(String __);
    public abstract Category build();
  }

  public static Builder builder() {
    return new AutoParcel_Category.Builder();
  }

  public abstract Builder toBuilder();

  public String baseImageName() {
    switch ((int) rootId()) {
      case 1:   return "art";
      case 3:   return "comics";
      case 26:  return "crafts";
      case 6:   return "dance";
      case 7:   return "design";
      case 9:   return "fashion";
      case 11:  return "film";
      case 10:  return "food";
      case 12:  return "games";
      case 13:  return "journalism";
      case 14:  return "music";
      case 15:  return "photography";
      case 18:  return "publishing";
      case 16:  return "technology";
      case 17:  return "theater";
      default:  return null;
    }
  }

  public @ColorInt int colorWithAlpha() {
    return KSColorUtils.setAlpha(color(), 255);
  }

  public int discoveryFilterCompareTo(@NonNull final Category other) {
    if (id() == other.id()) {
      return 0;
    }

    if (isRoot() && id() == other.rootId()) {
      return -1;
    } else if (!isRoot() && rootId() == other.id()) {
      return 1;
    }

    return root().name().compareTo(other.root().name());
  }

  public @Nullable Drawable imageWithOrientation(final Context context, final int orientation) {
    final String baseImageName = baseImageName();
    if (baseImageName == null) {
      return null;
    }

    final String name = "category_"
      + baseImageName
      + "_"
      + (ViewUtils.isPortrait(context) ? "portrait" : "landscape");

    final @DrawableRes int identifier = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    if (identifier == 0) {
      return null;
    }

    return ContextCompat.getDrawable(context, identifier);
  }

  public boolean isRoot() {
    return parentId() == null || parentId() == 0;
  }

  public Category root() {
    return isRoot() ? this : parent();
  }

  public long rootId() {
    return isRoot() ? id() : parentId();
  }

  public int secondaryColor(@NonNull final Context context) {
    final int identifier;
    switch ((int) rootId()) {
      case 1:   identifier = R.color.category_secondary_art; break;
      case 3:   identifier = R.color.category_secondary_comics; break;
      case 26:  identifier = R.color.category_secondary_crafts; break;
      case 6:   identifier = R.color.category_secondary_dance; break;
      case 7:   identifier = R.color.category_secondary_design; break;
      case 9:   identifier = R.color.category_secondary_fashion; break;
      case 11:  identifier = R.color.category_secondary_film; break;
      case 10:  identifier = R.color.category_secondary_food; break;
      case 12:  identifier = R.color.category_secondary_games; break;
      case 13:  identifier = R.color.category_secondary_journalism; break;
      case 14:  identifier = R.color.category_secondary_music; break;
      case 15:  identifier = R.color.category_secondary_photography; break;
      case 18:  identifier = R.color.category_secondary_publishing; break;
      case 16:  identifier = R.color.category_secondary_technology; break;
      case 17:  identifier = R.color.category_secondary_theater; break;
      default:  identifier = R.color.white; break;
    }

    return context.getResources().getColor(identifier);
  }

  public @ColorInt int overlayTextColor(final Context context) {
    return overlayShouldBeLight() ? KSColorUtils.lightColor(context) : KSColorUtils.darkColor(context);
  }

  public boolean overlayShouldBeDark() {
    switch ((int) rootId()) {
      case 1:
      case 3:
      case 14:
      case 15:
      case 18:  return true;
      default:  return false;
    }
  }

  public boolean overlayShouldBeLight() {
    return !overlayShouldBeDark();
  }
}
