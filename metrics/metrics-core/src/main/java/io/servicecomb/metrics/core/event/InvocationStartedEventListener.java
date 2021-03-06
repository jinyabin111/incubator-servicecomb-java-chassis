/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.servicecomb.metrics.core.event;

import io.servicecomb.core.metrics.InvocationStartedEvent;
import io.servicecomb.foundation.common.event.Event;
import io.servicecomb.foundation.common.event.EventListener;
import io.servicecomb.metrics.core.monitor.RegistryMonitor;

public class InvocationStartedEventListener implements EventListener {

  private final RegistryMonitor registryMonitor;

  public InvocationStartedEventListener(RegistryMonitor registryMonitor) {
    this.registryMonitor = registryMonitor;
  }

  @Override
  public Class<? extends Event> getConcernedEvent() {
    return InvocationStartedEvent.class;
  }

  @Override
  public void process(Event data) {
    InvocationStartedEvent event = (InvocationStartedEvent) data;
    registryMonitor.getInvocationMonitor(event.getOperationName()).getWaitInQueue().increment();
  }
}
