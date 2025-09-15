# Native Android SDK Exploration: A Tenjin Integration Journey (Kotlin & Jetpack Compose)

This repository documents a hands-on integration of the Tenjin SDK into a **native Android application** built with **Kotlin and Jetpack Compose**.

The primary purpose of this project is to serve as a direct **comparative analysis** to the parallel exploration conducted with the Tenjin Flutter SDK. It examines the developer experience, prerequisites, and data integrity from a native-first perspective.

You can view the original Flutter project and its findings here:
[**https://github.com/komangsidhiartha/tenjin_exploratory_sample_flutter**](https://github.com/komangsidhiartha/tenjin_exploratory_sample_flutter)

## The Native Integration Journey

### 1. Prerequisite: Dashboard Setup & Key Retrieval

Before any code can be written, the first step is to create an application within the Tenjin dashboard to obtain the necessary SDK Key. This presents the first point of friction in the onboarding flow, as the documentation jumps directly into code integration without first outlining this mandatory administrative step.

### 2. Project Setup: A Smoother Start

Unlike the initial Flutter setup which required resolving JDK and Gradle environment mismatches, the native Android project setup was straightforward. The environment, having been corrected during the first exploration, allowed for an immediate and successful initial build.

### 3. Core Integration & The Critical `AD_ID` Confirmation

The central goal of this exploration was to verify if the `AD_ID` permission is as critical for native Android as it is for Flutter.

**Investigation & Discovery:**
The test confirmed this hypothesis unequivocally.

1. **Without the Permission:** When the SDK was initialized and events were sent *without* the `com.google.android.gms.permission.AD_ID` permission in the `AndroidManifest.xml`, all events were attributed to the null `00000000-0000-0000-0000-000000000000` advertising ID.

2. **With the Permission:** After adding the permission, the SDK correctly captured the device's unique Google Advertising ID (GAID), and user-level data appeared correctly in the Tenjin dashboard.

This result proves that the `AD_ID` permission is a **fundamental requirement across all Android implementations** of the Tenjin SDK, not a quirk of the Flutter wrapper. Omitting it renders user-level attribution ineffective.

## Comparative Analysis: Flutter vs. Native Kotlin

This native exploration provides a clear basis for comparing the developer experience (DX) between the two platforms.

| Aspect | Flutter Integration | Native Kotlin Integration | 
 | ----- | ----- | ----- | 
| **Environment Setup** | Encountered initial hurdles with JDK and Gradle/Kotlin versions that required troubleshooting. | Seamless, as environment issues were previously resolved. | 
| **Integration Steps** | Simple: add dependency, initialize SDK with API key, send events. | Identical: add dependency, initialize SDK with API key, send events. The core logic is consistent. | 
| **Key "Gotcha"** | The critical need for the `AD_ID` permission was discovered through significant investigation. | The `AD_ID` requirement was quickly validated, confirming it's a universal Android prerequisite. | 
| **Documentation** | The documentation's omission of the `AD_ID` requirement was a major pain point. | The native documentation correctly states the requirement for the `AD_ID` permission. This highlights an inconsistency between the native and cross-platform (Flutter) guides. | 
| **Overall DX** | The core SDK is easy to use, but the initial experience was hampered by documentation gaps. | The core SDK is equally easy to use, and the experience was faster due to prior knowledge. | 

## Key Findings & Developer Experience (DX) Suggestions

This native Android exploration successfully validates and refines the findings from the initial Flutter project.

1. **Sync Documentation for `AD_ID` Requirement:** The native Android documentation correctly includes the `AD_ID` permission requirement. The Flutter documentation should be updated to match this, ensuring consistency and preventing a common pitfall for cross-platform developers.

2. **Clarify Dashboard Functionality:** The purpose of the "Live Test Device Data" view (requiring manual device registration) remains a point of potential confusion for first-time users on any platform.

## Conclusion

This native Android exploration successfully validates the findings from the initial Flutter project, confirming that the Tenjin SDK is functionally robust on both native and cross-platform stacks.

The core opportunity for improving the developer experience lies in enhancing documentation parity. By syncing the Flutter documentation with the already-correct native Android guide regarding the `AD_ID` permission, Tenjin can significantly streamline the onboarding process for cross-platform developers.