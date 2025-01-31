// Copyright 2021 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.firebase.firestore.index;

import com.google.protobuf.ByteString;

/**
 * Implements {@link DirectionalIndexByteEncoder} using {@link OrderedCodeWriter} for the actual
 * encoding.
 */
public class IndexByteEncoder extends DirectionalIndexByteEncoder {
  // Note: This code is copied from the backend.

  private final OrderedCodeWriter orderedCode;

  public IndexByteEncoder() {
    this.orderedCode = new OrderedCodeWriter();
  }

  public void seed(byte[] encodedBytes) {
    orderedCode.seed(encodedBytes);
  }

  @Override
  public void writeBytes(ByteString val) {
    orderedCode.writeBytesAscending(val);
  }

  @Override
  public void writeString(String val) {
    orderedCode.writeUtf8Ascending(val);
  }

  @Override
  public void writeLong(long val) {
    orderedCode.writeSignedLongAscending(val);
  }

  @Override
  public void writeDouble(double val) {
    orderedCode.writeDoubleAscending(val);
  }

  public byte[] getEncodedBytes() {
    return orderedCode.encodedBytes();
  }

  public void reset() {
    orderedCode.reset();
  }
}
