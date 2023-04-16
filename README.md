# VectorChildFinder
A repository to allows finding inner parts of VectorDrawable, and perform operations on them, based on VectorChildFinder repository and a fork of it :

- https://github.com/devendroid/VectorChildFinder
- https://github.com/m-melis/VectorChildFinder

## Advantages over VectorDrawable&VectorDrawableCompat :

1. Can find paths/groups by name, apply what you wish on them.
2. Can traverse entire hirerchy and apply what you wish on any node there.
3. Can add click-listener for paths of your choice
4. Due to always using the same inflation technique of the VectorDrawable, no matter the API, this should work the same for all Android versions

## Disadvantages:
As it's based on an old library, which is based on old code of VectorDrawableCompat, which can't deal with certain things that were added later, such as gradient:

- https://issuetracker.google.com/issues/276738333

Because of this, I hope one day VectorDrawable/VectorDrawableCompat would allow the same capabilities as I've presented here.
I've created requests for each:

- https://issuetracker.google.com/issues/276648059
- https://issuetracker.google.com/issues/275398348
- https://issuetracker.google.com/issues/278367262

## Usage
Same as the original ones, but with a bit more functionality that I've shown on the sample.
Also, to use in gradle file, have it as such:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
    dependencies {
        implementation 'com.github.devsideal:VectorChildFinder:#'
     }

This is done via Jitpack:
https://jitpack.io/#AndroidDeveloperLB/VectorChildFinder
