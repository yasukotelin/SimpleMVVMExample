package com.github.yasukotelin.simplemvvmexample

/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Copyright 2020 yasukotelin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.lifecycle.Observer

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class Event<out T>(private val content: T) {

    // for multiple observe.
    private val tagMap: MutableMap<String, Boolean> = mutableMapOf()

    // for single observe.
    private var hasBeenHandled = false

    /**
     * Returns the content if don't call this yet.
     * @param tag if you want to observe multiple, should specify tag.
     */
    fun getContentIfNotHandled(tag: String? = null): T? {
        return if (tag == null) {
            if (hasBeenHandled) {
                null
            } else {
                hasBeenHandled = true
                content
            }
        } else {
            if (tagMap[tag] == null) {
                tagMap[tag] = true
                content
            } else {
                null
            }
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

/**
 * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
 * already been handled.
 *
 * @param tag if you want to observe multiple, should specify tag.
 * @param onEventUnhandledContent is *only* called if the [Event]'s contents has not been handled.
 */
class EventObserver<T>(
    private val tag: String? = null,
    private val onEventUnhandledContent: (T) -> Unit
) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled(tag)?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}