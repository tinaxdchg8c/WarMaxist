/*******************************************************************************
 * Copyright 2011-2014 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.lib.loader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.*;
import com.nostra13.universalimageloader.core.DisplayBitmapTask;
import com.nostra13.universalimageloader.core.ImageLoadingInfo;
import com.nostra13.universalimageloader.core.LoadAndDisplayImageTask;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;
import com.nostra13.universalimageloader.utils.L;

/**
 * Presents process'n'display image task. Processes image {@linkplain Bitmap} and display it in {@link ImageView} using
 * {@link com.nostra13.universalimageloader.core.DisplayBitmapTask}.
 *
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 * @since 1.8.0
 */
final class ProcessAndDisplayImageTask implements Runnable {

	private static final String LOG_POSTPROCESS_IMAGE = "PostProcess image before displaying [%s]";

	private final com.nostra13.universalimageloader.core.ImageLoaderEngine engine;
	private final Bitmap bitmap;
	private final com.nostra13.universalimageloader.core.ImageLoadingInfo imageLoadingInfo;
	private final Handler handler;

	public ProcessAndDisplayImageTask(com.nostra13.universalimageloader.core.ImageLoaderEngine engine, Bitmap bitmap, com.nostra13.universalimageloader.core.ImageLoadingInfo imageLoadingInfo,
			Handler handler) {
		this.engine = engine;
		this.bitmap = bitmap;
		this.imageLoadingInfo = imageLoadingInfo;
		this.handler = handler;
	}

	@Override
	public void run() {
		L.d(LOG_POSTPROCESS_IMAGE, imageLoadingInfo.memoryCacheKey);

		BitmapProcessor processor = imageLoadingInfo.options.getPostProcessor();
		Bitmap processedBitmap = processor.process(bitmap);
		com.nostra13.universalimageloader.core.DisplayBitmapTask displayBitmapTask = new com.nostra13.universalimageloader.core.DisplayBitmapTask(processedBitmap, imageLoadingInfo, engine,
				LoadedFrom.MEMORY_CACHE);
		com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.runTask(displayBitmapTask, imageLoadingInfo.options.isSyncLoading(), handler, engine);
	}
}
