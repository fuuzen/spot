/*
 * Copyright 2024 Xebia Netherlands
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
 */
package com.xebia.data.spot

import io.opentelemetry.context.propagation.TextMapGetter

import java.lang
import scala.collection.JavaConverters._

/** Bridges between Spark config and OpenTelemetry's context propagator system.
  */
class GetContextFromConfig extends TextMapGetter[Map[String, String]] {
  override def keys(carrier: Map[String, String]): lang.Iterable[String] = carrier.keys
    .filter(_.startsWith(SPOT_CONFIG_PREFIX))
    .map(_.substring(SPOT_CONFIG_PREFIX.length))
    .asJava

  override def get(carrier: Map[String, String], key: String): String = carrier.get(configPrefixed(key)).orNull
}
